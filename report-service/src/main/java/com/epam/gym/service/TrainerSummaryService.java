package com.epam.gym.service;

import com.epam.gym.model.dto.TrainerSummaryDto;
import com.epam.gym.model.entity.TrainerSummary;

public interface TrainerSummaryService {
    void updateSummary(TrainerSummaryDto trainerSummaryDto);

    TrainerSummary getByUsername(String username);
}
