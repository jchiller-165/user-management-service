Feature: Get the User Details for a given user ID

  Background:
    Given an endpoint of "/api/v1/users"

  Scenario Outline: [1] A "POST"  request is sent to the endpoint to create a new user

    Given a request matching the template file, "<request>"
    When a "POST" request to the endpoint is sent
    Then the response has status code = 201 and has no body

    Examples:
      | request                                           |
      | requests/userdetails_POST_request_scenario_1.json |
