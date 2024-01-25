package com.epam.gym.repository.impl;

import com.epam.gym.model.entity.TrainerSummary;
import com.epam.gym.repository.TrainerSummaryRepo;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class TrainerSummaryRepoImpl implements TrainerSummaryRepo {
    Map<String, TrainerSummary> summary = new HashMap<>();

    @Override
    public TrainerSummary getByUsername(String username) {
        return summary.get(username);
    }

    @Override
    public void save(TrainerSummary trainerSummary) {
        summary.put(trainerSummary.getUsername(), trainerSummary);
    }
}
