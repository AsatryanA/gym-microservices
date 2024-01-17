package com.epam.reportservice.repository;

import com.epam.reportservice.model.dto.TrainerSummaryDto;
import com.epam.reportservice.model.entity.TrainerSummary;


public interface TrainerSummaryRepo {

    TrainerSummary getByUsername(String username);

    void save(TrainerSummary trainerSummary);

    void update(TrainerSummary existingTrainer, TrainerSummaryDto trainerSummaryDto);
}
