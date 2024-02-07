package com.epam.gym.cucumberGlue.steps;

import com.epam.gym.model.dto.response.UserResponseDTO;
import com.epam.gym.service.UserService;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RequiredArgsConstructor
public class UserSteps {

    private final UserService userService;
    private UserResponseDTO userResponse;
    private Map<String, String> credentials;

    @Given("the user request with valid credentials")
    public void theUserRequestWithValidDetails(DataTable dataTable) {
        credentials = dataTable.asMap(String.class, String.class);
    }

    @When("a user is created")
    public void aUserIsCreated() {
        userResponse = userService.create(credentials.get("firstName"), credentials.get("lastName"));
    }

    @Then("the user response should contain the credentials")
    public void theUserResponseShouldContainTheDetails(DataTable dataTable) {
        Map<String, String> map = dataTable.asMap(String.class, String.class);
        assertEquals(map.get("firstName"), userResponse.getFirstName());
        assertEquals(map.get("lastName"), userResponse.getLastName());
        assertEquals(map.get("isActive"), userResponse.getIsActive().toString());
        assertEquals(10, userResponse.getRawPassword().length());
        assertTrue(userResponse.getUsername()
                .startsWith(map.get("firstName") + "." + map.get("lastName")));
    }
    @Then("the user response should contain the firstName and lastName with count")
    public void theUserResponseShouldContainTheSameDetails(DataTable dataTable) {
        Map<String, String> map = dataTable.asMap(String.class, String.class);
        assertTrue(userResponse.getUsername()
                .startsWith(userResponse.getFirstName() + "." + userResponse.getLastName()));
    }
}
