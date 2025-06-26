Feature: Create the User Details for a given User

  Background:
    Given an endpoint of "/api/v1/users/{userId}"

  Scenario Outline: [1] A "PUT"  request is sent to the endpoint to update an existing user

    Given a path parameter "userId" with value "<userId>"
    And a request matching the template file, "<request>"
    When a "PUT" request to the endpoint is sent
    Then the response has status code = 204 and has no body

    Examples:
      |   userId    |                   request                                 |
      |      1      |      requests/userdetails_PUT_request_scenario_1.json     |