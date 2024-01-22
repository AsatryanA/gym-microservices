package com.epam.reportservice.repository;

import com.epam.reportservice.model.entity.TrainerSummary;


public interface TrainerSummaryRepo {

    TrainerSummary getByUsername(String username);

    void save(TrainerSummary trainerSummary);

}
