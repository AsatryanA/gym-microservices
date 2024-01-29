package com.epam.gym.service;

import com.epam.gym.model.dto.TrainerSummaryDto;
import com.epam.gym.model.entity.TrainerSummary;

import java.util.List;

public interface TrainerSummaryService {
    void updateSummary(TrainerSummaryDto trainerSummaryDto);

    List<TrainerSummary> getByFirstnameAndLastname(String firstname, String lastname);
}
