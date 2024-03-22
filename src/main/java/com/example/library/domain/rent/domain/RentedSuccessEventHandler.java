package com.example.library.domain.rent.domain;

import com.example.library.domain.book.service.BookService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Getter
@Component
@RequiredArgsConstructor
@Slf4j
public class RentedSuccessEventHandler {

    private final BookService bookService;

//    @EventListener(RentedSuccessEvent.class)
    @Async //즉 트랜잭션은 하나의 스레드에서만 관리(생성, 종료 등)될 수 있다.
    @Transactional(propagation = Propagation.REQUIRES_NEW) //해당 메소드 내에서 더티체킹이 되지 않는다. 이걸 해주기 위해 해당 트랜잭션 Requries_new를 세팅한다.
    @TransactionalEventListener(value =  RentedSuccessEvent.class
            ,phase = TransactionPhase.AFTER_COMMIT
    ) //AfterComiit은 해당 메소드를 호출한 메소드의 트랜잭션이 완료된 이후 비동기로 해당 메소드를 진행한다
    public void handle(RentedSuccessEvent evt){
        bookService.rentSuc(evt.getBookNo());
    }
}































//@Service
//@Transactional
//class MemberService(
//        private val memberRepository: MemberRepository,
//        private val mailService: MailService
//) {
//
//    fun registerProcess(registerMemberRequestData: RegisterMemberRequestData): RegisterMemberResponseData {
//
//        // 1. 회원 등록
//        val savedMember = register(registerMemberRequestData)
//
//        // 2. 가입 축하 메일 발송 (메일 발송 기록은 db에 저장)
//        mailService.sendSuccessRegisteredMemberMail(savedMember.id, savedMember.email)
//
//        return RegisterMemberResponseData(memberId = savedMember.id)
//    }
//
//    private fun register(requestData: RegisterMemberRequestData): Member {
//        val newMember = Member(nickname = requestData.nickname, email = requestData.mail)
//        return memberRepository.save(newMember)
//    }
//}
//
//@Service
//@Transactional
//class MailService(
//        private val emailSender: EmailSender,
//        private val emailSendHistoryRepository: EmailSendHistoryRepository
//) {
//
//    fun sendSuccessRegisteredMemberMail(memberId: Long?, emailAddress: String) {
//        //1. 메일을 보낸다
//        //2. DB에 저장한다.
//        val successRegisteredMEmber = SuccessRegisteredMemberMessageGenerator.generate(memberId)
//        emailSender.send(successRegisteredMEmber, emailAddress) //1.메일을 보낸다
//
//        val emailSendHistory = EmailSendHistory(
//                sendAt = LocalDateTime.now(),
//                message = successRegisteredMEmber,
//                targetId = memberId,
//                type = EmailType.MEMBER_REGISTER_SUCESS
//        )
//        emailSendHistoryRepository.save(emailSendHistory)
//    }
//}
