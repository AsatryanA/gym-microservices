Feature: User Service

  Scenario: Create a new user successfully
    Given the user request with valid credentials
      | firstName | John |
      | lastName  | Doe  |
    When a user is created
    Then the user response should contain the credentials
      | firstName | John     |
      | lastName  | Doe      |
      | username  | John.Doe |
      | isActive  | true     |

  Scenario: Create a user with an existing username
    Given the user request with valid credentials
      | firstName | John |
      | lastName  | Doe  |
    When a user is created
    Then the user response should contain the firstName and lastName with count
      | username | John.Doe |
