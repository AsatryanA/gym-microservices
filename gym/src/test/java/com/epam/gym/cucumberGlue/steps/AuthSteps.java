package com.epam.gym.cucumberGlue.steps;

import com.epam.gym.model.dto.request.ChangeLoginDTO;
import com.epam.gym.model.dto.request.LoginDTO;
import com.epam.gym.model.dto.response.LoginResponseDTO;
import com.epam.gym.service.AuthService;
import com.epam.gym.util.jwt.JwtProvider;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RequiredArgsConstructor
public class AuthSteps {

    private final AuthService authService;
    private LoginDTO loginDTO;
    private ChangeLoginDTO changeLoginDTO;
    private ResponseEntity<LoginResponseDTO> loginResponse;
    private ResponseEntity<Void> changeLoginResponse;

    @Given("a user with username {string} and password {string}")
    public void aUserWithUsernameAndPassword(String username, String password) {
        loginDTO = new LoginDTO();
        loginDTO.setUsername(username);
        loginDTO.setPassword(password);
    }
    @When("the user logs in")
    public void theUserLogsIn() {
        try {
            loginResponse = new ResponseEntity<>(authService.login(loginDTO), HttpStatus.OK);
        }catch (AuthenticationException e){
            loginResponse = new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @Then("the user receives a valid access token")
    public void theUserReceivesAValidAccessToken() {
        assertNotNull(loginResponse);
        assertEquals(HttpStatus.OK, loginResponse.getStatusCode());
        assertNotNull(loginResponse.getBody());
        assertNotNull(loginResponse.getBody().getToken());
        assertEquals("Bearer ", loginResponse.getBody().getTokenType());
    }
    @Then("the login fails with status code {int}")
    public void theLoginFailsWithStatusCode(int expectedStatusCode) {
        assertNotNull(loginResponse);
        assertEquals(expectedStatusCode, loginResponse.getStatusCodeValue());
    }

    @Given("a user with ID {string} and old password {string} and new password {string}")
    public void aUserWithIdAndOldPasswordAndNewPassword(String userId, String oldPassword, String newPassword) {
        changeLoginDTO = new ChangeLoginDTO();
        changeLoginDTO.setId(Long.parseLong(userId));
        changeLoginDTO.setOldPassword(oldPassword);
        changeLoginDTO.setNewPassword(newPassword);
    }

    @When("the user changes the login password")
    public void theUserChangesTheLoginPassword() {
        try{
            authService.changeLogin(changeLoginDTO);
            changeLoginResponse = new ResponseEntity<>(HttpStatus.OK);
        }catch (BadCredentialsException e){
            changeLoginResponse = new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @Then("the password is updated successfully")
    public void thePasswordIsUpdatedSuccessfully() {
        assertNotNull(changeLoginResponse);
        assertEquals(HttpStatus.OK, changeLoginResponse.getStatusCode());
    }

    @Then("the password change fails with status code {int}")
    public void thePasswordChangeFailsWithStatusCode(int expectedStatusCode) {
        assertNotNull(changeLoginResponse);
        assertEquals(expectedStatusCode, changeLoginResponse.getStatusCodeValue());
    }
}
