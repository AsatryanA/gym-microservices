Feature: Authentication Feature

  Scenario: User successfully logs in and receives a token
    Given a user with username "Arsen.Asatryan" and password "password"
    When the user logs in
    Then the user receives a valid access token

  Scenario: User fails to log in with invalid credentials
    Given a user with username "invalidUser" and password "invalidPassword"
    When the user logs in
    Then the login fails with status code 401

  Scenario: User successfully changes login password
    Given a user with ID "1" and old password "password" and new password "password"
    When the user changes the login password
    Then the password is updated successfully

  Scenario: User fails to change login password with incorrect old password
    Given a user with ID "1" and old password "incorrectOldPassword" and new password "password"
    When the user changes the login password
    Then the password change fails with status code 401

