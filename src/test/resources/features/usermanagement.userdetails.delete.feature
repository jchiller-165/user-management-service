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

  Scenario Outline: [2] A "DELETE"  request is sent to the endpoint to delete user details that does not exist

    Given a path parameter "userId" with value "<userId>"
    When a "DELETE" request to the endpoint is sent
    Then the response has status code = 404

    Examples:
      | userId |
      | 888    |

  Scenario Outline: [3] A "DELETE"  request is sent to the endpoint to delete user details with a blank user ID

    Given a path parameter "userId" with value "<userId>"
    When a "GET" request to the endpoint is sent
    Then the response has status code = 404

    Examples:
      | userId |
      |        |