Feature: Create the User Details for a given User

  Background:
    Given an endpoint of "/api/v1/users"

  Scenario Outline: [1] A "POST"  request is sent to the endpoint to create a new user

    Given a request matching the template file, "<request>"
    When a "POST" request to the endpoint is sent
    Then the response has status code = 201 and has no body

    Examples:
      | request                                           |
      | requests/userdetails_POST_request_scenario_1.json |

  Scenario Outline: [2] A "POST" an invalid request is sent to the endpoint to create a new user

    Given a request matching the template file, "<request>"
    When a "POST" request to the endpoint is sent
    Then the response has status code = <ErrorCode>
    And the response has error message matching "<ErrorMessage>"

    Examples:
      | request                                             | ErrorMessage                                        | ErrorCode |
      | requests/userdetails_POST_request_scenario_2_1.json | Conflict: company_name must be unique.              | 409       |
      | requests/userdetails_POST_request_scenario_2_2.json | Conflict: email must be unique.                     | 409       |
      | requests/userdetails_POST_request_scenario_2_3.json | Conflict: username must be unique.                  | 409       |
      | requests/userdetails_POST_request_scenario_2_4.json | username: Username is required                      | 400       |
      | requests/userdetails_POST_request_scenario_2_5.json | contactInfo.firstName: First Name is required       | 400       |
      | requests/userdetails_POST_request_scenario_2_6.json | companyDetails.address: Company address is required | 400       |
