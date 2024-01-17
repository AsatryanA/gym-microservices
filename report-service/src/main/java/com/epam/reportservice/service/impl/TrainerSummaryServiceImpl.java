package com.epam.reportservice.service.impl;

import com.epam.reportservice.mapper.TrainerSummaryMapper;
import com.epam.reportservice.model.dto.TrainerSummaryDto;
import com.epam.reportservice.repository.TrainerSummaryRepo;
import com.epam.reportservice.service.TrainerSummaryService;
import com.epam.reportservice.util.constants.ActionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TrainerSummaryServiceImpl implements TrainerSummaryService {

    private final TrainerSummaryRepo trainerSummaryRepo;
    private final TrainerSummaryMapper trainerSummaryMapper;

    @Override
    public void summary(TrainerSummaryDto trainerSummaryDto) {
        if (trainerSummaryDto.getActionType().equals(ActionType.DELETE)) {
            trainerSummaryDto.setDuration(-trainerSummaryDto.getDuration());
        }
        var existingTrainer = trainerSummaryRepo.getByUsername(trainerSummaryDto.getUsername());
        if (existingTrainer == null) {
            trainerSummaryRepo.save(trainerSummaryMapper.toTrainerSummary(trainerSummaryDto));
        } else {
            trainerSummaryRepo.update(existingTrainer, trainerSummaryDto);
        }
    }
}
