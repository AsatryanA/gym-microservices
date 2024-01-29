package com.epam.gym.service.impl;

import com.epam.gym.mapper.TrainerSummaryMapper;
import com.epam.gym.model.dto.TrainerSummaryDto;
import com.epam.gym.model.entity.TrainerSummary;
import com.epam.gym.repository.TrainerSummaryRepo;
import com.epam.gym.service.TrainerSummaryService;
import com.epam.gym.util.constants.ActionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TrainerSummaryServiceImpl implements TrainerSummaryService {

    private final TrainerSummaryRepo trainerSummaryRepo;
    private final TrainerSummaryMapper trainerSummaryMapper;

    @Override
    public void updateSummary(TrainerSummaryDto trainerSummaryDto) {
        var actionType = trainerSummaryDto.getActionType();
        var existingTrainer = trainerSummaryRepo.findByUsername(trainerSummaryDto.getUsername());
        if (existingTrainer == null) {
            if (actionType.equals(ActionType.DELETE)) {
                return;
            }
            trainerSummaryRepo.save(trainerSummaryMapper.toTrainerSummary(trainerSummaryDto));
        } else {
            if (actionType.equals(ActionType.DELETE)) {
                trainerSummaryDto.setDuration(-trainerSummaryDto.getDuration());
            }
            var updatedTraining = update(existingTrainer, trainerSummaryDto);
            trainerSummaryRepo.save(updatedTraining);
        }
    }

    @Override
    public List<TrainerSummary> getByFirstnameAndLastname(String firstname, String lastname) {
        return trainerSummaryRepo.findByFirstNameAndLastName(firstname,lastname);
    }

    private TrainerSummary update(TrainerSummary existingTrainer, TrainerSummaryDto trainerSummaryDto) {
        var year = trainerSummaryDto.getDate().getYear();
        var month = trainerSummaryDto.getDate().getMonthValue();
        existingTrainer
                .getDuration()
                .computeIfAbsent(year, k -> new HashMap<>())
                .merge(month, trainerSummaryDto.getDuration(), Integer::sum);
        return existingTrainer;
    }
}
