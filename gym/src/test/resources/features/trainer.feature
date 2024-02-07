Feature: Trainer Service

  Scenario: Create a new trainer
    Given the trainee request with valid credentials firstName "first",lastName "last", trainingTypeId 1
    When the create trainer
    Then the trainer is successfully created with a response containing an id, username, and password

  Scenario: Get trainer by ID
    Given a trainer with id 1 exists
    When the trainer with id is requested
    Then the response contains the  trainer details

  Scenario: Update trainer
    Given a trainer with id 5 exists
    When the trainer with id 5 is updated with firstName set to "Updated"
    Then the response contains the updated trainer details

#  Scenario: Get trainings for a trainer
#    Given a trainer with id 1 exists
#    When the trainings for the trainer with id 1 are requested
#    Then the response contains a list of training details for that trainer
#
#  Scenario: Toggle trainer active status
#    Given a trainer with id 1 exists
#    When the active status for the trainer with id 1 is toggled to false
#    Then the response shows that the trainer is inactive
