Feature: Update the User Details for a given User

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

  Scenario Outline: [2] A "PUT" an invalid request is sent to the endpoint to create a new user

    Given a path parameter "userId" with value "<userId>"
    And a request matching the template file, "<request>"
    When a "PUT" request to the endpoint is sent
    Then the response has status code = <ErrorCode>
    And the response has error message matching "<ErrorMessage>"

    Examples:
      |   userId     | request                                             | ErrorMessage                                        | ErrorCode |
      |      1       | requests/userdetails_PUT_request_scenario_2_1.json  | username: Username is required                      | 400       |
      |      1       | requests/userdetails_PUT_request_scenario_2_2.json  | contactInfo.firstName: First Name is required       | 400       |
      |      1       | requests/userdetails_PUT_request_scenario_2_3.json  | companyDetails.address: Company address is required | 400       |