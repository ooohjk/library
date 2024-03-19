package com.example.library.domain.rent.manager.service.Impl;

import com.example.library.domain.rent.manager.dto.RentManagerDto;
import com.example.library.domain.rent.manager.entity.RentManagerEntity;
import com.example.library.domain.rent.manager.repository.RentManagerRepository;
import com.example.library.domain.rent.manager.service.RentManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RentManagerServiceImpl implements RentManagerService {

    private final RentManagerRepository rentManagerRepository;

    @Override
    public List<RentManagerDto> getAllManager() {
        List<RentManagerEntity> rentManagerEntityList = rentManagerRepository.findAll();

        return rentManagerEntityList.stream()
                .map(m -> new RentManagerDto(
                        m.getManagerNo(), m.getUser().getUserNo(), m.getOverdue(), m.getRentNumber(), m.getLastReturnDt()
                ))
                .collect(Collectors.toList());
    }
}
