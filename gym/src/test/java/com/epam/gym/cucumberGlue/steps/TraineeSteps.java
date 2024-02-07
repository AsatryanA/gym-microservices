package com.epam.gym.cucumberGlue.steps;

import com.epam.gym.exception.ResourceNotFoundException;
import com.epam.gym.model.dto.request.ToggleActiveDTO;
import com.epam.gym.model.dto.request.TraineeRequestDTO;
import com.epam.gym.model.dto.request.TraineeUpdateDTO;
import com.epam.gym.model.dto.response.TraineeCreateResponseDTO;
import com.epam.gym.model.dto.response.TraineeResponseDTO;
import com.epam.gym.service.TraineeService;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RequiredArgsConstructor
public class TraineeSteps {


    private final TraineeService traineeService;
    private TraineeRequestDTO traineeRequest;
    private TraineeUpdateDTO traineeUpdateDTO;
    private boolean toggleActive;
    private ResponseEntity<TraineeCreateResponseDTO> traineeCreateResponseDTO;
    private ResponseEntity<TraineeResponseDTO> traineeResponseDTO;

    @Given("the trainee request with valid credentials")
    public void givenTraineeRequest(DataTable dataTable) {
        Map<String, String> map = dataTable.asMap(String.class, String.class);
        traineeRequest = new TraineeRequestDTO();
        traineeRequest.setFirstName(map.get("firstName"));
        traineeRequest.setLastName(map.get("lastName"));
        traineeRequest.setAddress(map.get("address"));
        traineeRequest.setDateOfBirth(LocalDate.parse(map.get("dateOfBirth")));
    }

    @When("the create trainee")
    public void whenCreateTrainee() {
        traineeCreateResponseDTO = new ResponseEntity<>(traineeService.create(traineeRequest), HttpStatus.CREATED);
    }

    @Then("the response should contain the trainee credentials and status code {int}")
    public void thenResponseShouldContainDetails(int statusCode) {
        assertNotNull(traineeCreateResponseDTO);
        assertEquals(traineeCreateResponseDTO.getStatusCode().value(), statusCode);
        assertTrue(traineeCreateResponseDTO.getBody().getUsername()
                .startsWith(traineeRequest.getFirstName() + "." + traineeRequest.getLastName()));
        assertEquals(traineeCreateResponseDTO.getBody().getPassword().length(), 10);
    }

    @Given("an existing trainee with ID")
    public void givenExistingTrainee() {
    }

    @When("the get trainee by ID {long}")
    public void whenGetTraineeById(Long id) {
        try {
            traineeResponseDTO = new ResponseEntity<>(traineeService.getById(id), HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            traineeResponseDTO = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Then("the response should contain the trainee credentials")
    public void thenResponseShouldContainTraineeDetails() {
        assertNotNull(traineeResponseDTO);
        assertEquals(traineeResponseDTO.getStatusCode().value(), HttpStatus.OK.value());
    }

    @Then("the response should contain status code not found")
    public void thenResponseShouldContainNotFound() {
        assertNotNull(traineeResponseDTO);
        assertEquals(traineeResponseDTO.getStatusCode().value(), HttpStatus.NOT_FOUND.value());
    }

    @Given("an existing trainee with for update")
    public void givenExistingTraineeForUpdate(DataTable dataTable) {
        Map<String, String> map = dataTable.asMap(String.class, String.class);
        traineeUpdateDTO = TraineeUpdateDTO.builder()
                .isActive(true)
                .address(map.get("address"))
                .firstName(map.get("firstName"))
                .lastName(map.get("lastName"))
                .id(Long.parseLong(map.get("id")))
                .dateOfBirth(LocalDate.parse(map.get("dateOfBirth")))
                .build();
    }

    @When("the update trainee is called with new information")
    public void whenUpdateTrainee() {
        try {
            traineeResponseDTO = new ResponseEntity<>(traineeService.update(traineeUpdateDTO), HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            traineeResponseDTO = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Then("the response should contain the updated trainee credentials")
    public void thenResponseShouldContainUpdatedTraineeDetails() {
        assertNotNull(traineeResponseDTO);
        assertEquals(traineeResponseDTO.getStatusCode().value(), HttpStatus.OK.value());
        assertEquals(traineeResponseDTO.getBody().getFirstName(), "UpdatedJohn");
        assertEquals(traineeResponseDTO.getBody().getLastName(), "UpdatedDoe");
    }

    @Given("an existing trainee with toggleActive true")
    public void givenExistingTraineeForToggleActive() {
        this.toggleActive = true;
    }

    @When("the toggle trainee active status is called with new status id {long}")
    public void whenToggleActiveTrainee(Long id) {
        traineeService.toggleActive(ToggleActiveDTO.builder()
                .isActive(toggleActive)
                .id(id)
                .build());
        try {
            traineeResponseDTO = new ResponseEntity<>(HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            traineeResponseDTO = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Then("the trainee's active status should be updated in the system")
    public void thenResponseShouldUpdateTraineeToggleActive() {
        assertNotNull(traineeResponseDTO);
        assertEquals(traineeResponseDTO.getStatusCode().value(), HttpStatus.OK.value());
    }
}
