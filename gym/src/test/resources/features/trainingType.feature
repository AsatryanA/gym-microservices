Feature: Training Type Service

  Scenario: Get all training types successfully
    Given there are existing training types
    When I request all training types
    Then the response should contain a list of training types
    And each training type in the list should have an id and a name

  Scenario: Get training type by valid ID successfully
    Given there is a training type with ID 1
    When I request the training type with ID 1
    Then the response should contain the training type credentials
    And the response should have the ID 1
    And the response should have a non-empty name

  Scenario: Get training type by invalid ID
    Given there is no training type with ID 1000
    When I request the training type with ID 1000
    Then a training type not found exception should be thrown



