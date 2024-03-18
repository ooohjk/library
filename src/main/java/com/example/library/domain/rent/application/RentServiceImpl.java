package com.example.library.domain.rent.application;

import com.example.library.domain.book.service.BookService;
import com.example.library.domain.rent.domain.RentManager;
import com.example.library.domain.rent.infrastructure.RentHistoryRepository;
import com.example.library.domain.rent.infrastructure.RentManagerEntity;
import com.example.library.domain.rent.infrastructure.RentManagerRepository;
import com.example.library.exception.ErrorCode;
import com.example.library.exception.exceptions.RentManagerNotFoudException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class RentServiceImpl implements RentService{

    private final BookService bookService;
    private final RentManagerRepository rentManagerRepository;
    private final RentHistoryRepository rentHistoryRepository;

    public void createRentManager(Long userNo){
        log.info(String.format("[createRentManager] rent Manager 생성: 유저번호[%s]",userNo.toString()));

        RentManager rentManager = RentManager.createRentManager()
                    .userNo(userNo)
                    .build()
                ;
        rentManagerRepository.save(rentManager.toNewEntity());

        log.info("[createRentManager] rent Manager 생성 완료");
    }

    @Transactional
    public void rentBook(Long userNo,Long bookNo) {
        log.info(String.format("[rentBook] 도서 대여 요청: 유저번호[%s]/도서번호[%s]",userNo.toString(),bookNo.toString()));

        RentManager rentManager = RentManager.from(getRentManagerByUserNo(userNo));

        rentManager.checkRentAvailable();
        bookService.checkRentAvailable(bookNo);
        rentManager.rent(bookNo); // 대여권수 필드 변경
        bookService.rentSuc(bookNo); //bookState DB 변경이벤트 발생 -> 쓰기영역에 저장 / 커밋 전

        rentManagerRepository.save(rentManager.toOldEntity());
        rentHistoryRepository.save(rentManager.getRentHistoryList().get(0).toEntity());

        log.info("[rentBook] 도서 대여 성공"); //커밋 진행
    }

    private RentManagerEntity getRentManagerByUserNo(Long userNo){
        log.info(String.format("rent Manager 조회 요청 - userNo[%s]",userNo.toString()));

        return rentManagerRepository.findByUserNo(userNo)
                .orElseThrow(()->new RentManagerNotFoudException(ErrorCode.RENTMANAGER_USERNO_NOT_FOUND))
                ;
    }

}
