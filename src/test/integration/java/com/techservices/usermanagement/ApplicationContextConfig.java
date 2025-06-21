package com.techservices.usermanagement;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

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
