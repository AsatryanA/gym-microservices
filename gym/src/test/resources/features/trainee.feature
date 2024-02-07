Feature: Trainee Feature

  Scenario: Create a new trainee
    Given the trainee request with valid credentials
      | firstName   | John       |
      | lastName    | Doe        |
      | dateOfBirth | 2025-10-11 |
      | address     | add        |
    When the create trainee
    Then the response should contain the trainee credentials and status code 201

  Scenario: Get trainee by ID
    Given an existing trainee with ID
    When the get trainee by ID 1
    Then the response should contain the trainee credentials

  Scenario: Get trainee by not existing ID
    Given an existing trainee with ID
    When the get trainee by ID 1111
    Then the response should contain status code not found

  Scenario: Update trainee
    Given an existing trainee with for update
      | id          | 1           |
      | firstName   | UpdatedJohn |
      | lastName    | UpdatedDoe  |
      | dateOfBirth | 2024-10-11  |
      | address     | UpdatedAdd  |
    When the update trainee is called with new information
    Then the response should contain the updated trainee credentials

  Scenario: Toggle trainee active status
    Given an existing trainee with toggleActive true
    When the toggle trainee active status is called with new status id 1
    Then the trainee's active status should be updated in the system

#  Scenario: Delete trainee
#    Given an existing trainee with ID
#    When the delete trainee API is called
#    Then the trainee should be deleted from the system


