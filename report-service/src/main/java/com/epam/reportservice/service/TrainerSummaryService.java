package com.epam.reportservice.service;

import com.epam.reportservice.model.dto.TrainerSummaryDto;
import org.springframework.stereotype.Service;

public interface TrainerSummaryService {
    void summary(TrainerSummaryDto trainerSummaryDto);
}
