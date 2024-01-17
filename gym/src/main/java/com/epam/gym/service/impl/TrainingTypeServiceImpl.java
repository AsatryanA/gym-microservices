package com.epam.gym.service.impl;

import com.epam.gym.exception.ResourceNotFoundException;
import com.epam.gym.feign.ReportClient;
import com.epam.gym.mapper.TrainingTypeMapper;
import com.epam.gym.model.TrainingType;
import com.epam.gym.model.dto.response.TrainingTypeResponseDTO;
import com.epam.gym.repository.TrainingTypeRepository;
import com.epam.gym.service.TrainingTypeService;
import com.epam.gym.util.jwt.JwtProvider;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TrainingTypeServiceImpl implements TrainingTypeService {

    private final TrainingTypeRepository trainingTypeRepository;
    private final TrainingTypeMapper trainingTypeMapper;
    private final ReportClient reportClient;
    private final JwtProvider jwtProvider;

    @Transactional
    @CircuitBreaker(name = "getAll", fallbackMethod = "getTest")
    public List<TrainingTypeResponseDTO> getAll(Pageable pageable) {
        // fake call to report service for testing circuit breaker
        reportClient.test(jwtProvider.getToken());
        return trainingTypeRepository.findAll(pageable).stream().map(trainingTypeMapper::toTrainingTypeResponseDto).toList();
    }

    @Override
    public TrainingType getById(Long id) {
        return trainingTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(TrainingType.class, id)
                );
    }

    //fake method for circuit breaker testing
    private List<TrainingTypeResponseDTO> getTest(Throwable t) {
        return List.of(TrainingTypeResponseDTO.builder().build());
    }
}
