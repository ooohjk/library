package com.example.library.domain.rent.service.Impl;

import com.example.library.domain.rent.dto.RentDto;
import com.example.library.domain.rent.entity.RentEntity;
import com.example.library.domain.rent.repository.RentRepository;
import com.example.library.domain.rent.service.RentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RentServiceImpl implements RentService {
    private final RentRepository rentRepository;

    @Override
    public List<RentDto> getAllRent() {
        List<RentEntity> rentEntityList = rentRepository.findAll();
        return rentEntityList.stream()
                .map(m -> new RentDto())
                .collect(Collectors.toList());
    }
}
