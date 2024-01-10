package com.epam.reportservice.model.dto;

import com.epam.reportservice.util.ActionType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class TrainerSummaryDto {
    private String username;
    private String firstName;
    private String lastName;
    private Boolean isActive;
    private LocalDate date;
    private Integer duration;
    private ActionType actionType;
}
