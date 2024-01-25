package com.epam.gym.repository;

import com.epam.gym.model.entity.TrainerSummary;


public interface TrainerSummaryRepo {

    TrainerSummary getByUsername(String username);

    void save(TrainerSummary trainerSummary);

}
