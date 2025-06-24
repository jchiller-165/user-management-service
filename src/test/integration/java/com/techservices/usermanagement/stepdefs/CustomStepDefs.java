package com.techservices.usermanagement.stepdefs;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;

import net.javacrumbs.jsonunit.core.Option;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

@SpringBootTest
public class CustomStepDefs {

  @Autowired
  protected TestRestTemplate restTemplate;

  protected String endPoint;
  protected ResponseEntity<String> lastResponse;
  protected Map<String, String> pathParams = new HashMap<>();

  @Given("an endpoint of {string}")
  public void anEndpointOf(String endPoint) {
    this.endPoint = endPoint;
  }

  @Given("a path parameter {string} with value {string}")
  public void aPathParameterWithValue(String param, String value) {
    pathParams.put(param, value);
  }

  @When("a {string} request to the endpoint is sent")
  public void aRequestToTheEndpointIsSent(String method) {
    String resolvedEndpoint = endPoint;
    for (Map.Entry<String, String> entry : pathParams.entrySet()) {
      resolvedEndpoint = resolvedEndpoint.replace("{" + entry.getKey() + "}", entry.getValue());
    }
    HttpMethod httpMethod = HttpMethod.valueOf(method.toUpperCase());
    lastResponse = restTemplate.exchange(resolvedEndpoint, httpMethod, HttpEntity.EMPTY, String.class);
  }

  @Then("the response has status code = {int}")
  public void theResponseHasStatusCode(int expectedStatus) {
    assertThat(lastResponse.getStatusCode().value()).isEqualTo(expectedStatus);
  }

  @Then("the response includes the data in the file {string}")
  public void theResponseIncludesTheDataInTheFile(String fileName) throws Exception {
    File file = ResourceUtils.getFile("classpath:" + fileName);
    String expectedJson = new String(Files.readAllBytes(file.toPath()));
    String actualJson = lastResponse.getBody();
    assertThatJson(actualJson).when(Option.IGNORING_EXTRA_FIELDS).isEqualTo(expectedJson);
  }

}
