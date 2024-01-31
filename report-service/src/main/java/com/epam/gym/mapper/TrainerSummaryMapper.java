package com.epam.gym.mapper;

import com.epam.gym.model.dto.TrainerSummaryDto;
import com.epam.gym.model.entity.TrainerSummary;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component

public class TrainerSummaryMapper {
    public TrainerSummary toTrainerSummary(TrainerSummaryDto trainerSummaryDto) {
        var month = trainerSummaryDto.getDate().getMonthValue();
        var year = trainerSummaryDto.getDate().getYear();
        Map<Integer, Integer> months = new HashMap<>();
        Map<Integer, Map<Integer, Integer>> years = new HashMap<>();
        months.put(month, trainerSummaryDto.getDuration());
        years.put(year, months);
        return TrainerSummary.builder()
                .firstName(trainerSummaryDto.getFirstName())
                .lastName(trainerSummaryDto.getLastName())
                .username(trainerSummaryDto.getUsername())
                .duration(years)
                .status(trainerSummaryDto.getIsActive())
                .build();
    }

}
