package com.epam.reportservice.repository.impl;

import com.epam.reportservice.model.dto.TrainerSummaryDto;
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
        System.out.println(summary.get(trainerSummary.getUsername()));
        summary.put(trainerSummary.getUsername(), trainerSummary);
        System.out.println(summary.get(trainerSummary.getUsername()));

    }

    @Override
    public void update(TrainerSummary existingTrainer, TrainerSummaryDto trainerSummaryDto) {
        System.out.println(summary.get(existingTrainer.getUsername()));
        var year = trainerSummaryDto.getDate().getYear();
        var month = trainerSummaryDto.getDate().getMonthValue();
        existingTrainer
                .getDuration()
                .computeIfAbsent(year, k -> new HashMap<>())
                .merge(month, trainerSummaryDto.getDuration(), Integer::sum);
        summary.put(existingTrainer.getUsername(), existingTrainer);
        System.out.println(summary.get(existingTrainer.getUsername()));
    }
}
