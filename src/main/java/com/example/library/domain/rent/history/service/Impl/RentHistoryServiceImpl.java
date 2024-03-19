package com.example.library.domain.rent.history.service.Impl;

import com.example.library.domain.book.entity.BookEntity;
import com.example.library.domain.book.repository.BookRepository;
import com.example.library.domain.rent.history.dto.RentHistoryDto;
import com.example.library.domain.rent.history.entity.RentHistoryEntity;
import com.example.library.domain.rent.history.repository.RentHistoryRepository;
import com.example.library.domain.rent.history.service.RentHistoryService;
import com.example.library.domain.rent.manager.entity.RentManagerEntity;
import com.example.library.domain.rent.manager.repository.RentManagerRepository;
import com.example.library.domain.user.repository.UserRepository;
import com.example.library.exception.ErrorCode;
import com.example.library.exception.exceptions.BookNotFoundException;
import com.example.library.exception.exceptions.RentException;
import com.example.library.exception.exceptions.UserNotFoundException;
import com.example.library.exception.exceptions.UserOverdueException;
import com.example.library.global.utils.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class RentHistoryServiceImpl implements RentHistoryService {
    private final RentHistoryRepository rentHistoryRepository;
    private final RentManagerRepository rentManagerRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    @Override
    public List<RentHistoryDto> getAllRent() {
        List<RentHistoryEntity> rentHistoryEntityList = rentHistoryRepository.findAll();

        return rentHistoryEntityList.stream()
                .map(m -> new RentHistoryDto(m.getRentNo(), m.getUser().getUserNo(), m.getBook().getBookCode(), m.getBook().getBookName(), m.getRentDt(), m.getProspectDt(), m.getReturnDt(), m.getExtension(), m.getRentState()))
                .collect(Collectors.toList());
    }

    @Override
    public List<RentHistoryDto> getMylist(Long userNo) {
        List<RentHistoryEntity> rentHistoryEntityList = rentHistoryRepository.findAllByUserUserNo(userNo);

        return rentHistoryEntityList.stream()
                .map(m -> new RentHistoryDto(m.getRentNo(), m.getUser().getUserNo(), m.getBook().getBookCode(), m.getBook().getBookName(), m.getRentDt(), m.getProspectDt(), m.getReturnDt(), m.getExtension(), m.getRentState()))
                .collect(Collectors.toList());
    }

    @Override
    public RentHistoryDto add(Long userNo, Long bookCode) throws ParseException {
        BookEntity book = bookRepository.findByBookCode(bookCode)
                .orElseThrow(() -> new BookNotFoundException(ErrorCode.BOOKCODE_NOT_FOUND));

        RentManagerEntity rentManager = rentManagerRepository.findByUserUserNo(userNo); // 매니저에서 사용자 연체 여부 판단을 위한 데이터 불러오기

        if(book.getBookState() == 0 && !rentManager.getOverdue() && rentManager.getRentNumber() < 2) { // 책상태 : 대여가능, 유저 연체x, 도서 2권이하
            book.setBookState(1);
            bookRepository.save(book);

            RentHistoryEntity rentHistoryEntity = RentHistoryEntity.builder()
                    .user(userRepository.findByUserNo(userNo)
                            .orElseThrow(() -> new UserNotFoundException(ErrorCode.USERNO_NOT_FOUND)))
                    .book(bookRepository.findByBookCode(bookCode)
                            .orElseThrow(() -> new BookNotFoundException(ErrorCode.BOOKCODE_NOT_FOUND)))
                    .rentDt(DateUtil.getDate())
                    .prospectDt(
                            new SimpleDateFormat("yyyyMMdd").format(
                                    new SimpleDateFormat("yyyyMMdd").parse(
                                            String.valueOf(
//                                                    (Integer.parseInt(DateUtil.getDate()) + 6) // 실제
                                                    (Integer.parseInt("20240310") + 6) // 테스트용
                                            )
                                    )
                            )
                    )   // 현재날짜(String) 받아와서 정수로 변환 후 +일주일, 그걸 다시 Date 포맷으로 바꾸고 String값으로 다시변경... 좀 번거롭다...
                    .returnDt(null)
                    .extension(false)
                    .rentState(0)
                    .build();
            rentManager.setRentNumber(rentManager.getRentNumber() + 1);
            rentHistoryRepository.save(rentHistoryEntity);
            rentManagerRepository.save(rentManager);

            return RentHistoryDto.info(rentHistoryEntity);
        } else if (book.getBookState() == 1) { // 책상태 : 대여 불가능
            throw new BookNotFoundException(ErrorCode.BOOK_ALREADY_RENT);
        } else if (rentManager.getOverdue()) { // 유저 연체o
            throw new UserOverdueException(ErrorCode.NOT_ALLOW_RENT);
        } else { // 2권 이상
            throw new RentException(ErrorCode.ALREADY_MAX_RENT);
        }
    }

    @Override
    public RentHistoryDto returnBook(Long userNo, Long bookCode) throws ParseException {
        List<RentHistoryEntity> rentHistoryEntity = rentHistoryRepository.findAllByUserUserNoAndBookBookCode(userNo, bookCode);
        BookEntity book = bookRepository.findByBookCode(bookCode)
                .orElseThrow(() -> new BookNotFoundException(ErrorCode.BOOKCODE_NOT_FOUND));
        RentHistoryEntity rentHistory = rentHistoryEntity.get(rentHistoryEntity.size() - 1);
        RentManagerEntity rentManager = rentManagerRepository.findByUserUserNo(userNo);

        if(book.getBookState() == 1 && rentManager.getRentNumber() > 0) { // 책상태 : 대여중인 상태, 유저 대여권수 > 0
            book.setBookState(0);
            bookRepository.save(book);

            if(rentHistory.getRentState() == 0) { // 유저 : 반납x
//                rentHistory.setReturnDt(DateUtil.getDate()); // 실제
                rentHistory.setReturnDt("20240317"); // 테스트용

                if(new SimpleDateFormat("yyyyMMdd").parse(rentHistory.getReturnDt())  // 실제반납날짜 < 반납예정일자 (연체x)
                        .before(new SimpleDateFormat("yyyyMMdd").parse(rentHistory.getProspectDt()))) {
                    rentHistory.setRentState(1);
                } else { // 실제반납날짜 > 반납예정일자 (연체o)
                    rentHistory.setRentState(2);
                }
                rentManager.setLastReturnDt(rentHistory.getReturnDt());
                rentManager.setRentNumber(rentManager.getRentNumber() - 1);
                rentManagerRepository.save(rentManager);
                rentHistoryRepository.save(rentHistory);

                return RentHistoryDto.info(rentHistory);
            } else { // 유저 : 반납o
                throw new RentException(ErrorCode.BOOK_ALREADY_RETURN);
            }
        } else if (book.getBookState() == 0) { // 책상태 : 반납된 상태
            throw new RentException(ErrorCode.BOOK_ALREADY_RETURN);
        } else { // 더 이상 반납할 책x
            throw new RentException(ErrorCode.ALREADY_MIN_RETURN);
        }
    }

    @Override
    public RentHistoryDto extension(Long userNo, Long bookCode) throws ParseException {
        List<RentHistoryEntity> rentHistoryEntity = rentHistoryRepository.findAllByUserUserNoAndBookBookCode(userNo, bookCode);
        RentHistoryEntity rentHistory = rentHistoryEntity.get(rentHistoryEntity.size() - 1);

        if(!rentHistory.getExtension()) {
            rentHistory.setExtension(true);
            rentHistory.setProspectDt(
                    new SimpleDateFormat("yyyyMMdd").format(
                            new SimpleDateFormat("yyyyMMdd").parse(
                                    String.valueOf(
                                            (Integer.parseInt(rentHistory.getProspectDt()) + 6)
                                    )
                            )
                    )
            );
            rentHistoryRepository.save(rentHistory);

            return RentHistoryDto.info(rentHistory);
        } else {
            throw new RentException(ErrorCode.ALREADY_EXTENSION);
        }
    }
}
