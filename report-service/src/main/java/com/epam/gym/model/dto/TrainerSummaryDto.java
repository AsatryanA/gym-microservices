package com.epam.gym.model.dto;

import com.epam.gym.util.constants.ActionType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class TrainerSummaryDto implements Serializable {
    @NotNull
    @NotBlank
    private String username;
    private String firstName;
    private String lastName;
    @NotNull
    private Boolean isActive;
    private LocalDate date;
    @Positive
    private Integer duration;
    @NotNull
    private ActionType actionType;
}
