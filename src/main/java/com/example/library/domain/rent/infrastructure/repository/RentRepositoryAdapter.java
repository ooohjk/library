package com.example.library.domain.rent.infrastructure.repository;

import com.example.library.domain.rent.RentState;
import com.example.library.domain.rent.domain.RentHistory;
import com.example.library.domain.rent.domain.RentManager;
import com.example.library.domain.rent.domain.RentRepository;
import com.example.library.domain.rent.infrastructure.entity.RentHistoryEntity;
import com.example.library.domain.rent.infrastructure.entity.RentManagerEntity;
import com.example.library.exception.ErrorCode;
import com.example.library.exception.exceptions.BookOnRentException;
import com.example.library.exception.exceptions.RentManagerNotFoudException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RentRepositoryAdapter implements RentRepository {

    private final SpringDataJpaRentManagerRepository rentManagerRepository;
    private final SpringDataJpaRentHistoryRepository rentHistoryRepository;

    @Override
    public RentManager save(RentManager rentManager) {
        RentManagerEntity mangerEntity = rentManagerRepository.save(convert(rentManager));
        if(rentManager.getRentHistory()!=null){
            RentHistoryEntity historyEntity = rentHistoryRepository.save(convert(rentManager.getRentHistory()));
        }

        return convert(mangerEntity);
    }

    @Override
    public RentManager findRentManagerByUserNo(Long userNo) {
        RentManagerEntity entity = rentManagerRepository.findByUserNo(userNo)
                .orElseThrow(()->new RentManagerNotFoudException(ErrorCode.RENTMANAGER_USERNO_NOT_FOUND));
        return convert(entity);
    }

    @Override
    public RentManager findRentManagerWithRentedBookHistory(Long userNo, Long bookNo) {
        RentManagerEntity managerEntity = rentManagerRepository.findByUserNo(userNo)
                .orElseThrow(()->new RentManagerNotFoudException(ErrorCode.RENTMANAGER_USERNO_NOT_FOUND));
        RentHistoryEntity historyEntity = rentHistoryRepository.findByUserNoAndBookNoAndRentState(userNo,bookNo, RentState.ON_RENT)
                .orElseThrow(()->new BookOnRentException(ErrorCode.BOOK_NOT_FOUND_AMONG_BOOKS_ON_RENT));
        RentManager managerDomain = convert(managerEntity);
        RentHistory historyDomain = convert(historyEntity);

        managerDomain.setRentHistory(historyDomain);

        return managerDomain;
    }

    private RentManager convert(RentManagerEntity entity){
        return RentManager.by(entity);
    }

    private RentManagerEntity convert(RentManager domain){
        return RentManagerEntity.builder()
                .managerNo(domain.getManagerNo())
                .userNo(domain.getUserNo())
                .currentRentNumber(domain.getCurrentRentNumber())
                .overdueFlg(domain.isOverdue())
                .build()
                ;
    }

    private RentHistory convert(RentHistoryEntity rentHistoryEntity){
        return RentHistory.by(rentHistoryEntity);
    }

    private RentHistoryEntity convert(RentHistory domain){
        return RentHistoryEntity.builder()
                .historyNo(domain.getHistoryNo())
                .userNo(domain.getUserNo())
                .managerNo(domain.getManagerNo())
                .bookNo(domain.getBookNo())
                .rentDt(domain.getRentDt())
                .returnDt(domain.getReturnDt())
                .haveToReturnDt(domain.getHaveToReturnDt())
                .extensionFlg(domain.isExtensionFlg())
                .rentState(domain.getRentState())
                .build()
                ;

    }
}
