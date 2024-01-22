package com.epam.reportservice.service;

import com.epam.reportservice.model.dto.TrainerSummaryDto;
import com.epam.reportservice.model.entity.TrainerSummary;

public interface TrainerSummaryService {
    void summary(TrainerSummaryDto trainerSummaryDto);

    TrainerSummary getByUsername(String username);
}
