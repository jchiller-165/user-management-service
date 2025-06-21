package com.techservices.usermanagement;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:features",
        glue = "com.techservices.usermanagement.stepdefs",
        plugin = {"pretty", "summary"}
)
public class RunCucumberIT {

    @Test
    void contextLoads() {
        // This method is used to ensure that the Spring context loads correctly.
        // It will be executed by the Cucumber runner.
    }

}
