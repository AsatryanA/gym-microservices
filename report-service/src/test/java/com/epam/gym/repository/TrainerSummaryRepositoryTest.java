package com.epam.gym.repository;

import com.epam.gym.model.entity.TrainerSummary;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.mongodb.assertions.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataMongoTest()
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
class TrainerSummaryRepositoryTest {

    @Autowired
    private TrainerSummaryRepo trainerSummaryRepo;
    @Autowired
    private MongoTemplate mongoTemplate;
    private TrainerSummary trainerSummary;

    @BeforeEach
    void setUp() {
        trainerSummary = createTrainerSummary();
    }

    @Test
    void givenNewTrainerSummary_whenSave_thenSuccess() {
        mongoTemplate.save(trainerSummary);
        TrainerSummary savedSummary = mongoTemplate.findById(trainerSummary.getId().toString(),TrainerSummary.class);
        assertEquals(trainerSummary, savedSummary);
    }

    @Test
    void givenTrainerSummaryId_whenGetById_thenSuccess() {
        mongoTemplate.save(trainerSummary);
        Optional<TrainerSummary> savedSummary = trainerSummaryRepo.findById(trainerSummary.getId());
        assertTrue(savedSummary.isPresent());
        assertEquals(trainerSummary.getId(), savedSummary.get().getId());
    }

    @Test
    void givenTrainerSummary_whenUpdate_thenSuccess() {
        mongoTemplate.save(trainerSummary);
        trainerSummary.setFirstName("UpdatedFirstName");
        mongoTemplate.save(trainerSummary);
        Optional<TrainerSummary> updatedSummary = trainerSummaryRepo.findById(trainerSummary.getId());
        assertTrue(updatedSummary.isPresent());
        assertEquals("UpdatedFirstName", updatedSummary.get().getFirstName());
    }

    @Test
    void givenFirstname_Lastname_whenGet_thenSuccess() {
        mongoTemplate.save(trainerSummary);
        List<TrainerSummary> fetchedSummary = trainerSummaryRepo.findByFirstNameAndLastName(trainerSummary.getFirstName(), trainerSummary.getLastName());
        assertTrue(fetchedSummary.contains(trainerSummary));
    }

    private TrainerSummary createTrainerSummary() {
        Map<Integer, Map<Integer, Integer>> year = new HashMap<>();
        Map<Integer, Integer> month = new HashMap<>();
        month.put(1, 50);
        year.put(2024, month);
        return TrainerSummary.builder()
                .username("John.Doe")
                .firstName("John")
                .lastName("Doe")
                .duration(year)
                .status(true)
                .build();
    }
}
