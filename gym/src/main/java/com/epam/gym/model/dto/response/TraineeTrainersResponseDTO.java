package com.epam.gym.model.dto.response;

import com.epam.gym.model.TrainingType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TraineeTrainersResponseDTO {
    private String username;
    private String firstName;
    private String lastName;
    private TrainingType specialization;

}
