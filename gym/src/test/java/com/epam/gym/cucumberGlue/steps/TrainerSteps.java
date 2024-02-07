package com.epam.gym.cucumberGlue.steps;

import com.epam.gym.model.TrainingType;
import com.epam.gym.model.dto.request.TrainerRequestDTO;
import com.epam.gym.model.dto.request.TrainerUpdateDTO;
import com.epam.gym.model.dto.response.TrainerCreateResponseDTO;
import com.epam.gym.model.dto.response.TrainerResponseDTO;
import com.epam.gym.model.dto.response.TrainerTrainingDTO;
import com.epam.gym.service.TrainerService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;
import javax.transaction.Transactional;
import java.util.List;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RequiredArgsConstructor
public class TrainerSteps {

    private final TrainerService trainerService;

    private TrainerRequestDTO trainerRequestDTO;
    private TrainerCreateResponseDTO trainerCreateResponseDTO;
    private TrainerResponseDTO trainerResponseDTO;
    private List<TrainerTrainingDTO> trainerTrainings;
    private TrainerResponseDTO updatedTrainer;
    private Long trainerId;

    @Given("the trainee request with valid credentials firstName {string},lastName {string}, trainingTypeId {long}")
    public void the_trainee_request_with_valid_credentials_first_name_last_name_training_type_id(String firstName, String lastName, Long trainingTypeId) {
        trainerRequestDTO = new TrainerRequestDTO();
        trainerRequestDTO.setFirstName(firstName);
        trainerRequestDTO.setLastName(lastName);
        trainerRequestDTO.setSpecialization(TrainingType.builder().id(trainingTypeId).build());
    }

    @When("the create trainer")
    public void the_create_trainer() {
        trainerCreateResponseDTO = trainerService.create(trainerRequestDTO);
    }

    @Then("the trainer is successfully created with a response containing an id, username, and password")
    public void the_trainer_is_successfully_created_with_a_response_containing_an_id_username_and_password() {
        assertNotNull(trainerCreateResponseDTO);
        assertNotNull(trainerCreateResponseDTO.getId());
        assertNotNull(trainerCreateResponseDTO.getUsername());
        assertNotNull(trainerCreateResponseDTO.getPassword());
    }

    @Given("a trainer with id {long} exists")
    public void a_trainer_with_id_exists(Long trainerId) {
        this.trainerId = trainerId;
    }
    @When("the trainer with id is requested")
    public void the_trainer_with_id_is_requested() {
        trainerResponseDTO = trainerService.getById(trainerId);
    }

    @Then("the response contains the  trainer details")
    public void  the_response_contains_the_trainer_details() {
        assertNotNull(trainerResponseDTO);
        assertNotNull(trainerResponseDTO.getFirstName());
        assertNotNull(trainerResponseDTO.getLastName());
        assertTrue(trainerResponseDTO.getIsActive());
    }
    @When("the trainer with id {long} is updated with firstName set to {string}")
    @Transactional
    public void the_trainer_with_id_is_updated_with_first_name_set_to(Long trainerId, String firstName) {
        var trainerById = trainerService.getById(trainerId);
        TrainerUpdateDTO trainerUpdateDTO = new TrainerUpdateDTO();
        trainerUpdateDTO.setId(trainerId);
        trainerUpdateDTO.setIsActive(trainerById.getIsActive());
        trainerUpdateDTO.setFirstName(firstName);
        trainerUpdateDTO.setLastName(trainerById.getLastName());
        trainerUpdateDTO.setSpecialization(trainerById.getSpecialization());
        updatedTrainer = trainerService.update(trainerUpdateDTO);
    }
    @Then("the response contains the updated trainer details")
    public void the_response_contains_the_updated_trainer_details() {
        assertNotNull(updatedTrainer);
        assertNotNull(updatedTrainer.getFirstName());
        assertNotNull(updatedTrainer.getLastName());
        assertNotNull(updatedTrainer.getIsActive());
    }

/*    @When("the trainings for the trainer with id {int} are requested")
    public void whenTrainingsForTrainerAreRequested(int trainerId) {
        trainerTrainings = trainerService.getTrainings((long) trainerId);
    }

    @When("the active status for the trainer with id {int} is toggled to {boolean}")
    public void whenToggleActiveStatusForTrainer(int trainerId, boolean isActive) {
        ToggleActiveDTO toggleActiveDTO = new ToggleActiveDTO();
        toggleActiveDTO.setId((long) trainerId);
        toggleActiveDTO.setIsActive(isActive);
        trainerService.toggleActive(toggleActiveDTO);
    }

    @Then("the response shows that the trainer is inactive")
    public void thenResponseShowsTrainerIsInactive() {
        // Implement assertions based on your response structure or use appropriate methods from your testing framework
        assertFalse(trainer.getUser().getIsActive());
    }*/

}
