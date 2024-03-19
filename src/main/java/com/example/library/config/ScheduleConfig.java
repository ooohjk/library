package com.example.library.config;

import com.example.library.domain.rent.manager.repository.RentManagerRepository;
import com.example.library.domain.user.entity.UserEntity;
import com.example.library.domain.user.repository.UserRepository;
import com.example.library.exception.ErrorCode;
import com.example.library.exception.exceptions.UserMailSendFailException;
import com.example.library.global.mail.sendMail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailSendException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class ScheduleConfig {

    private final UserRepository userRepository;
    private final RentManagerRepository rentManagerRepository;
    private Map<Long, Boolean> map = new HashMap<>();

    @Scheduled(cron = "0 10 0 * * *") // 매일 00:10:00 실행예정
    public void test() {
        List<UserEntity> user = userRepository.findAll();
        user.forEach((u) -> {
            Long userNo = u.getUserNo();
            Boolean overdue = rentManagerRepository.findByUserUserNo(userNo) != null && rentManagerRepository.findByUserUserNo(userNo).getOverdue();
            if(map.size() != 0) {
                try {
                    if(!map.get(userNo) && overdue) { // 정상에서 연체로 바뀐 유저들 (연체등록) false & true
                        sendMail.send("register", u.getUserEmail(), u.getUserName());
                    } else if(map.get(userNo) && !overdue) { // 연체에서 정상으로 바뀐 유저들 (연체해제) true & false
                        sendMail.send("clear", u.getUserEmail(), u.getUserName());
                    } else if(map.get(userNo) && overdue) { // 연체에서 연체 그대로 (연체중) true & true
                        sendMail.send("overdue", u.getUserEmail(), u.getUserName());
                    }
                } catch (MailSendException e) {
                    throw new UserMailSendFailException(ErrorCode.MAIL_SEND_FAIL);
                }
            }
        });
    }

    @Scheduled(cron = "0 59 23 * * *") // 매일 23:59:00 실행예정
    public void overdueCheck() {
        List<UserEntity> user = userRepository.findAll();

        user.forEach((u) -> {
            Long userNo = u.getUserNo();
            Boolean overdue = rentManagerRepository.findByUserUserNo(userNo) != null && rentManagerRepository.findByUserUserNo(userNo).getOverdue();
            map.put(userNo, overdue);
        });
    }
}
