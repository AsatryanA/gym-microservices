package com.epam.gym.model.entity;

import com.epam.gym.util.constants.TrainerStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TrainerSummary {
    private String username;
    private String firstName;
    private String lastName;
    private TrainerStatus status;
    private Map<Integer, Map<Integer, Integer>> duration;
}
