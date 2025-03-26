Feature: User API Tests

  @Order(1)
  Scenario: Create a new user
    Given create new users with details:
      | firstName | lastName   | email             | dateOfBirth | documentId | validUntil  | countryOfIssue |
      | Mareks    | Piternieks | mareks@mintos.com | 14.04.1990. | LV012345   | 29.07.2025. | LV             |
      | Janis     | Berzins    | janis@mintos.com  | 10.02.1991. | LV012999   | 12.07.2028. | US             |
      | Merike    | Janac      | merike@mintos.com | 11.07.1988. | LV01229    | 12.07.2029. | EE             |
    Then user checks response status for POST request is 201

  Scenario: Invalid User Creation
    Given create new users with details:
      | firstName | lastName   | email             | dateOfBirth | documentId | validUntil  | countryOfIssue |
      | Anna      | B          | anna@mintos.com   | 14.04.1998. | LV099999   | 29.07.2025. | LV             |
    Then user checks response status for POST request is 400
    And check that response contains message "lastName must be between 2 and 50 characters"
    Given create new users with details:
      | firstName | lastName   | email             | dateOfBirth | documentId | validUntil  | countryOfIssue |
      | Anna      | Bunte      |                   | 14.04.1998. | LV099999   | 29.07.2025. | LV             |
    Then user checks response status for POST request is 400
    And check that response contains message "email is missing"
    Given create new users with details:
      | firstName | lastName   | email             | dateOfBirth | documentId | validUntil  | countryOfIssue |
      | Anna      | Bunte      | anna@mintos.com   | 14.04.1998. |            | 29.07.2025. | LV             |
    Then user checks response status for POST request is 400
    And check that response contains message "documentId is missing"
    Given create new users with details:
      | firstName | lastName   | email             | dateOfBirth | documentId | validUntil  | countryOfIssue |
      | Anna      | Bunte      | anna@mintos.com   | 14.04.1998. | LV099999   | 29.07.2025. | L              |
    Then user checks response status for POST request is 400
    And check that response contains message "countryOfIssue must be an ISO 3166-1 alpha-2 code"

  @Order(2)
  Scenario: Get all users
    Given retrieve all users
    Then user checks response status for GET request is 200
    And verify there is 3 users retrieved

  @Order(3)
  Scenario: Get a user by ID
    Given retrieve user with ID "2"
    Then user checks response status for GET request is 200
    And response "firstName" should contain "Janis"

  Scenario: Retrieve a non-existing user
    Given retrieve user with ID "999"
    Then user checks response status for GET request is 404

  @Order(4)
  Scenario: Update a user by ID
    Given update user "2" field "firstName" to "Andris"
    Then user checks response status for PATCH request is 200
    Given retrieve user with ID "2"
    And response "firstName" should contain "Andris"

  Scenario: Update a non-existing user
    Given update user "999" field "firstName" to "John"
    Then user checks response status for PATCH request is 404

  Scenario: Delete a user by ID
    Given delete user with ID "3"
    Then user checks response status for DELETE request is 204

  Scenario: Delete a non-existing user
    Given delete user with ID "999"
    Then user checks response status for DELETE request is 404
