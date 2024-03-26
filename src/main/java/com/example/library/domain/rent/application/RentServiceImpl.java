package com.example.library.domain.rent.application;

import com.example.library.domain.book.entity.BookEntity;
import com.example.library.domain.book.service.BookService;
import com.example.library.domain.rent.application.dto.UserRentStatusResDto;
import com.example.library.domain.rent.domain.*;
import com.example.library.global.Events;
import com.example.library.global.eventListener.SendedMailEvent;
import com.example.library.global.mail.enums.MailType;
import com.example.library.global.mail.mailHistory.MailDto;
import com.example.library.global.mail.mailHistory.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class RentServiceImpl implements RentService{

    private final BookService bookService;   //도서도메인 외부API라고 가정
    private final RentRepository rentRepository;
    private final MailService mailService;

    public void createRentManager(Long userNo){
        log.info(String.format("[createRentManager] rent Manager 생성: 유저번호[%s]",userNo.toString()));

        RentManager rentManager = RentManager.createRentManager()
                .userNo(userNo)
                .build()
                ;
        rentRepository.save(rentManager);

        log.info("[createRentManager] rent Manager 생성 완료");
    }

    //두 개의 에그리거트가 변경되므로 응용서비스에 각각 변경되야할 각 애그리거트로 진행
    @Override
    @Transactional
    public void rentBook(Long userNo,Long bookNo) {
        BookEntity book = bookService.getBookDetail(bookNo);
        RentManager rentManager = rentRepository.findRentManagerByUserNo(userNo);
        book.checkRentAvailable();
        bookService.rentSuc(book.getBookCode());
        rentManager.rentBook(bookNo);
        rentRepository.save(rentManager);
        Events.raise(new SendedMailEvent(new MailDto(userNo, MailType.MAIL_RENT)));
    }
    
    @Override
    @Transactional //외부API 도서 상태 변경 롤백에 대한 처리를 모르므로 유지
    public void returnBook(Long userNo, Long bookNo) {
        RentManager rentManager = rentRepository.findRentManagerWithRentedBookHistory(userNo,bookNo);
        rentManager.returnBook();
        bookService.returnSuc(bookNo);
        rentRepository.save(rentManager);
        Events.raise(new SendedMailEvent(new MailDto(userNo,MailType.MAIL_RETURN)));
    }

    @Override
    @Transactional //익셉션은 다 잡았다는 가정 하에 해낭 어노테이션 주석
    public void extendBook(Long userNo,Long bookNo){
        RentManager rentManager = rentRepository.findRentManagerWithRentedBookHistory(userNo,bookNo);
        // 만약 도서 예약이 존재했다면,
        // 도서 연장했지만 도서예약이 잡혀있다면 거절내야 한다라는 요구사항 등판
        rentManager.extendBook();
        rentRepository.save(rentManager);
        Events.raise(new SendedMailEvent(new MailDto(userNo,MailType.MAIL_EXTEND)));
    }

    public List<UserRentStatusResDto> getCurrentRentStatus(Long userNo){
        List<RentHistory> rentHistoryList = rentRepository.findUserRentStatus(userNo);
        List<UserRentStatusResDto> userRentStatusResDtos = rentHistoryList.stream()
                .map(rentHistory -> UserRentStatusResDto.from(rentHistory))
                .collect(Collectors.toList());
        return userRentStatusResDtos;
    }

}
