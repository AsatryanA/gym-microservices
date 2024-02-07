package com.epam.gym.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;

@Configuration
@RequiredArgsConstructor
public class InitData {

    private final JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void initializeDatabase() {
        final var traineeCountQuery = "SELECT COUNT(*) FROM training_types";
        try {
            var rowCount = jdbcTemplate.queryForObject(traineeCountQuery, Integer.class);
            if (rowCount != null && rowCount == 0) {
                var trainingTypes = new ClassPathResource("data/training_types.sql");
                var user = new ClassPathResource("data/user.sql");
                var trainer = new ClassPathResource("data/trainer.sql");
                var trainee = new ClassPathResource("data/trainee.sql");
                var traineeTrainer = new ClassPathResource("data/trainee_trainer.sql");
                var training = new ClassPathResource("data/training.sql");

                var sqlScript = new String(Files.readAllBytes(trainingTypes.getFile().toPath()));
                var sqlScript1 = new String(Files.readAllBytes(user.getFile().toPath()));
                var sqlScript2 = new String(Files.readAllBytes(trainer.getFile().toPath()));
                var sqlScript3 = new String(Files.readAllBytes(trainee.getFile().toPath()));
                var sqlScript4 = new String(Files.readAllBytes(traineeTrainer.getFile().toPath()));
                var sqlScript5 = new String(Files.readAllBytes(training.getFile().toPath()));
                jdbcTemplate.execute(sqlScript);
                jdbcTemplate.execute(sqlScript1);
                jdbcTemplate.execute(sqlScript2);
                jdbcTemplate.execute(sqlScript3);
                jdbcTemplate.execute(sqlScript4);
                jdbcTemplate.execute(sqlScript5);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}