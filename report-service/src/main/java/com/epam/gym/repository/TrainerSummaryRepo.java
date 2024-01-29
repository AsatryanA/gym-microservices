package com.epam.gym.repository;

import com.epam.gym.model.entity.TrainerSummary;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface TrainerSummaryRepo extends MongoRepository<TrainerSummary, String> {

    TrainerSummary findByUsername(String username);

    List<TrainerSummary> findByFirstNameAndLastName(String firstname, String lastname);
}
