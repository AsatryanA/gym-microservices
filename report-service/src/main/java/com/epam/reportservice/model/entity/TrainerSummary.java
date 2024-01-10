package com.epam.reportservice.model.entity;

import com.epam.reportservice.util.TrainerStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TrainerSummary {
    private String username;
    private String firstName;
    private String lastName;
    private TrainerStatus status;
    private Map<Integer, Map<Integer, Integer>> duration;
}
