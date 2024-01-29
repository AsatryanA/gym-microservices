package com.epam.gym.model.dto;

import com.epam.gym.util.constants.ActionType;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class TrainerSummaryDto implements Serializable {
    private String username;
    private String firstName;
    private String lastName;
    private Boolean isActive;
    private LocalDate date;
    private Integer duration;
    private ActionType actionType;
}
