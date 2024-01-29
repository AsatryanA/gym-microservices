package com.epam.gym.model.dto.request;

import com.epam.gym.util.ActionType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Builder
public class TrainerSummaryDto implements Serializable {
    private String username;
    private String firstName;
    private String lastName;
    private Boolean isActive;
    private LocalDate date;
    private Integer duration;

    private ActionType actionType;
}
