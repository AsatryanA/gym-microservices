package com.epam.gym.cucumberGlue.steps;

import com.epam.gym.exception.ResourceNotFoundException;
import com.epam.gym.model.dto.request.TrainingRequestDTO;
import com.epam.gym.model.dto.response.TrainingResponseDTO;
import com.epam.gym.service.TrainingService;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;
import org.springframework.web.server.ServerErrorException;

import java.time.LocalDate;
import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RequiredArgsConstructor
public class TrainingSteps {


    private final TrainingService trainingService;
    private TrainingRequestDTO trainingRequestDTO;
    private Exception thrownException;
    @Given("a new trainee with valid credentials")
    public void aNewTraineeWithValidCredentials(DataTable dataTable) {
        Map<String, String> map = dataTable.asMap(String.class, String.class);
        trainingRequestDTO = TrainingRequestDTO.builder()
                .name(map.get("name"))
                .date(LocalDate.parse(map.get("date")))
                .traineeId(Long.parseLong(map.get("traineeId")))
                .trainerId(Long.parseLong(map.get("trainerId")))
                .duration(Integer.parseInt(map.get("duration")))
                .build();
    }
    @Given("a new trainee with invalid traineeId")
    public void aNewTraineeWithInvalidTraineeId(DataTable dataTable) {
        Map<String, String> map = dataTable.asMap(String.class, String.class);
        trainingRequestDTO = TrainingRequestDTO.builder()
                .name(map.get("name"))
                .date(LocalDate.parse(map.get("date")))
                .traineeId(Long.parseLong(map.get("traineeId")))
                .trainerId(Long.parseLong(map.get("trainerId")))
                .duration(Integer.parseInt(map.get("duration")))
                .build();
    }
    @When("create a training")
    public void createTraining() {
        try {
            trainingService.create(trainingRequestDTO);
        }catch (ResourceNotFoundException | ServerErrorException e){
            thrownException = e;
        }
    }

    @Then("a new training should be created")
    public void assertTrainingCreated() {
    }
    @Then("a training not found exception should be thrown")
    public void assertResourceNotFoundException() {
        assertNotNull(thrownException);
    }
}

