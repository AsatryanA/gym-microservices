package com.epam.gym.model.entity;

import com.epam.gym.util.constants.TrainerStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection = "trainer_summary")
@CompoundIndex(name = "firstname_lastname",def = "{'firstName': 1, 'lastName': 1}")
public class TrainerSummary {
    @Id
    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private TrainerStatus status;
    private Map<Integer, Map<Integer, Integer>> duration;
}
