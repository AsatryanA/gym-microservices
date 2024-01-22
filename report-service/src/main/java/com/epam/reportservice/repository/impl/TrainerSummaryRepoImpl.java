package com.epam.reportservice.repository.impl;

import com.epam.reportservice.model.entity.TrainerSummary;
import com.epam.reportservice.repository.TrainerSummaryRepo;
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
