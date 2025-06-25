package com.techservices.usermanagement.stepdefs;

import org.springframework.boot.test.context.SpringBootTest;

import io.cucumber.spring.CucumberContextConfiguration;

@CucumberContextConfiguration
@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    properties = {
        "spring.profiles.active=integration"
    }
)
public class ApplicationContextConfig {
  // This class is used to configure the Spring context for Cucumber tests.
  // It specifies the application class and the active profile for integration tests.
}
