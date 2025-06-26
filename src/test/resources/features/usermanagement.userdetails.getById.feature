Feature: Get the User Details for a given user ID

  Background:
    Given an endpoint of "/api/v1/users/{userId}"

  Scenario Outline: [1] A "GET"  request is sent to the endpoint to retrieve user details

    Given a path parameter "userId" with value "<userId>"
    When a "GET" request to the endpoint is sent
    Then the response has status code = 200
    And the response includes the data in the file "<response>"

    Examples:
      | userId | response                                   |
      | 1      | responses/user_details_GET_scenario_1.json |

  Scenario Outline: [2] A "GET"  request is sent to the endpoint to retrieve user details that does not exist

    Given a path parameter "userId" with value "<userId>"
    When a "GET" request to the endpoint is sent
    Then the response has status code = 404

    Examples:
      | userId |
      | 999    |

  Scenario Outline: [3] A "GET"  request is sent to the endpoint to retrieve user details with a blank user ID

    Given a path parameter "userId" with value "<userId>"
    When a "GET" request to the endpoint is sent
    Then the response has status code = 404

    Examples:
      | userId |
      |        |