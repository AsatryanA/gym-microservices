package com.epam.gym.service;

import com.epam.gym.model.dto.request.TrainerSummaryDto;
import com.epam.gym.model.entity.TrainerSummary;

public interface TrainerSummaryService {
    void summary(TrainerSummaryDto trainerSummaryDto);

    TrainerSummary getByUsername(String username);
}
