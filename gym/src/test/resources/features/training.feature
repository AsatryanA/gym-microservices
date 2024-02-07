Feature: Training Service

  Scenario: Create a training successfully
    Given a new trainee with valid credentials
      | traineeId | 1          |
      | trainerId | 1          |
      | name      | Boxing     |
      | date      | 2024-09-12 |
      | duration  | 60         |
    When create a training
    Then a new training should be created


  Scenario: Create a training with invalid trainee ID
    Given a new trainee with invalid traineeId
      | traineeId | 1500       |
      | trainerId | 85         |
      | name      | Boxing     |
      | date      | 2024-09-12 |
      | duration  | 60         |
    When create a training
    Then a training not found exception should be thrown

#  Scenario: Delete a training successfully
#    Given there is an existing training with ID 1
#    When I delete the training with ID 1
#    Then the training should be deleted
#    And a message should be sent to the trainer with the deletion details
#
#  Scenario: Delete a non-existing training
#    Given there is no training with ID 99
#    When I delete the training with ID 99
#    Then a resource not found exception should be thrown
