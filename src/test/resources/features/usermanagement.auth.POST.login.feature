Feature: Login User Management API

  Background:
    Given an endpoint of "/auth/login"

  Scenario Outline: [1] A "POST" request is sent to the endpoint to login a user

    Given a request matching the template file, "<request>"
    When a "POST" request to the endpoint is sent
    Then the response has status code = 200
    And the response contains a valid JWT for user "<username>" with role "<role>" and userId "<userId>"

    Examples:
      | request                                           |   username    |    role    |   userId    |
      | requests/usermanagement_auth_POST_scenario_1.json |   testuser    |   admin    |     1       |

  Scenario Outline: [2] A "POST" request is sent to the endpoint to login a user that does not exist

    Given a request matching the template file, "<request>"
    When a "POST" request to the endpoint is sent
    Then the response has status code = 404

    Examples:
      | request                                           |
      | requests/usermanagement_auth_POST_scenario_2.json |

  Scenario Outline: [3] A "POST" request is sent to the endpoint to login a user with incorrect password

    Given a request matching the template file, "<request>"
    When a "POST" request to the endpoint is sent
    Then the response has status code = <ErrorCode>
    And the response has error message matching "<ErrorMessage>"

    Examples:
      | request                                           |       ErrorMessage          | ErrorCode |
      | requests/usermanagement_auth_POST_scenario_3.json |     Invalid Credentials     |    400    |

  Scenario Outline: [4] A "POST" request is sent to the endpoint that fails due to missing credentials

    Given a request matching the template file, "<request>"
    When a "POST" request to the endpoint is sent
    Then the response has status code = <ErrorCode>
    And the response has error message matching "<ErrorMessage>"

    Examples:
      | request                                             |       ErrorMessage              | ErrorCode |
      | requests/usermanagement_auth_POST_scenario_4_1.json |     Username cannot be blank    |    400    |
