package com.epam.reportservice.service.impl;

import com.epam.reportservice.model.dto.TrainerSummaryDto;
import com.epam.reportservice.repository.TrainerSummaryRepo;
import com.epam.reportservice.service.TrainerSummaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TrainerSummaryServiceImpl implements TrainerSummaryService {

    private final TrainerSummaryRepo trainerSummaryRepo;
    @Override
    public void create(TrainerSummaryDto trainerSummaryDto) {

    }
}
