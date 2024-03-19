package com.example.library.domain.rent.application;

import com.example.library.domain.book.entity.BookEntity;
import com.example.library.domain.book.service.BookService;
import com.example.library.domain.rent.RentState;
import com.example.library.domain.rent.domain.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class RentServiceImpl implements RentService{

    private final BookService bookService;   //도서도메인 외부API라고 가정
    private final RentRepository rentRepository;

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
    @Transactional//외부API 도서 상태 변경 롤백에 대한 처리를 모르므로 유지
    public void rentBook(Long userNo,Long bookNo) {
        log.info(String.format("[rentBook] 도서 대여 요청: 유저번호[%s]/도서번호[%s]",userNo.toString(),bookNo.toString()));

        //이벤트로부터 북 도메인 가져옴
        BookEntity book = bookService.getBookDetail(bookNo);
        RentManager rentManager = rentRepository.findRentManagerByUserNo(userNo);

        //가능? 아래와 같이 rentManager 애그리거트에서 book 애그리거트 필드값으로 상태 검증이 가능한가?
//        rentManager.rentBook(bookNo,book);

        //도더 대여가능여부를 북 도메인에서 진행
        book.checkRentAvailable();
        //대여
        rentManager.rentBook(bookNo);

        //bookState 변경이벤트 발생 -> 쓰기영역에 저장 / 커밋 전,
        // 도메인 상태 변화를 알리기 위해 북API로 요청해야하기 때문에 유지
        bookService.rentSuc(bookNo); //외부API에 bookNo 넘겨주면 알아서 변경

        rentRepository.save(rentManager);

        log.info("[rentBook] 도서 대여 성공"); //커밋 진행
    }
    @Override
    @Transactional //외부API 도서 상태 변경 롤백에 대한 처리를 모르므로 유지
    public void returnBook(Long userNo, Long bookNo) {
        log.info(String.format("[return] 도서 반납 요청: 유저번호[%s]/도서번호[%s]",userNo.toString(),bookNo.toString()));

        RentManager rentManager = rentRepository.findRentManagerWithRentedBookHistory(userNo,bookNo);

        //반납
        rentManager.returnBook();

        //외부API 통해 변경 진행
        bookService.returnSuc(bookNo);

        rentRepository.save(rentManager);

        log.info("[return] 도서 반납 성공");
    }

    @Override
//    @Transactional //익셉션은 다 잡았다는 가정 하에 해낭 어노테이션 주석
    public void extendBook(Long userNo,Long bookNo){
         log.info(String.format("[return] 도서 연장 요청: 유저번호[%s]/도서번호[%s]",userNo.toString(),bookNo.toString()));

        RentManager rentManager = rentRepository.findRentManagerWithRentedBookHistory(userNo,bookNo);

        // 만약 도서 예약이 존재했다면, 
        // 도서 연장했지만 도서예약이 잡혀있다면 거절내야 한다라는 요구사항 등판
        rentManager.extendBook();

        rentRepository.save(rentManager);

        log.info("[return] 도서 연장 성공");
    }
}
