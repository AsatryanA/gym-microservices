package com.epam.gym.cucumberGlue.steps;

import com.epam.gym.exception.ResourceNotFoundException;
import com.epam.gym.mapper.TrainingTypeMapper;
import com.epam.gym.model.dto.response.TrainingTypeResponseDTO;
import com.epam.gym.service.TrainingTypeService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.junit.Assert.*;
@RequiredArgsConstructor
public class TrainingTypeSteps {

    private final TrainingTypeService trainingTypeService;
    private final TrainingTypeMapper mapper;
    private List<TrainingTypeResponseDTO> trainingTypesResponse;
    private TrainingTypeResponseDTO singleTrainingTypeResponse;
    private Exception thrownException;
    private Long id;

    @Given("there are existing training types")
    public void createExistingTrainingTypes() {
    }
    @When("I request all training types")
    public void requestAllTrainingTypes() {
        trainingTypesResponse = trainingTypeService.getAll(PageRequest.of(0,50));
    }
    @Then("the response should contain a list of training types")
    public void assertTrainingTypesList() {
        assertNotNull(trainingTypesResponse);
        assertFalse(trainingTypesResponse.isEmpty());
    }
    @Then("each training type in the list should have an id and a name")
    public void assertTrainingTypeDetailsInList() {
        for (TrainingTypeResponseDTO trainingType : trainingTypesResponse) {
            assertNotNull(trainingType.getId());
            assertNotNull(trainingType.getName());
        }
    }
    @Given("there is a training type with ID {long}")
    public void setTrainingTypeWithId(Long id) {
        this.id  = id;
    }
    @When("I request the training type with ID {long}")
    public void requestTrainingTypeById(Long id) {
        try {
            singleTrainingTypeResponse = mapper.toTrainingTypeResponseDto(trainingTypeService.getById(id));
        } catch (Exception e) {
            thrownException = e;
        }
    }
    @Then("the response should contain the training type credentials")
    public void assertSingleTrainingTypeDetails() {
        assertNotNull(singleTrainingTypeResponse);
        assertNotNull(singleTrainingTypeResponse.getId());
        assertNotNull(singleTrainingTypeResponse.getName());
    }
    @Then("the response should have the ID {long}")
    public void assertTrainingTypeId(Long expectedId) {
        assertEquals(expectedId, singleTrainingTypeResponse.getId());
    }
    @Then("the response should have a non-empty name")
    public void assertTrainingTypeName() {
        assertNotNull(singleTrainingTypeResponse.getName());
        assertFalse(singleTrainingTypeResponse.getName().isEmpty());
    }
    @Given("there is no training type with ID {long}")
    public void setNonExistentTrainingTypeId(Long id) {
        this.id = id;
    }
    @Then("a training type not found exception should be thrown")
    public void assertResourceNotFoundException() {
        assertNotNull(thrownException);
        assertTrue(thrownException instanceof ResourceNotFoundException);
    }
}
