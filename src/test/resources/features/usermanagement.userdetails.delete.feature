Feature: Delete the User Details for a given User

  Background:
    Given an endpoint of "/api/v1/users/{userId}"

  Scenario Outline: [1] A "DELETE"  request is sent to the endpoint to update an existing user

    Given a path parameter "userId" with value "<userId>"
    When a "DELETE" request to the endpoint is sent
    Then the response has status code = 204 and has no body

    Examples:
      |   userId    |
      |    999      |