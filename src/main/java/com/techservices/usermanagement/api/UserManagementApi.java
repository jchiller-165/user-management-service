package com.techservices.usermanagement.api;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.techservices.usermanagement.models.UserDetails;
import com.techservices.usermanagement.models.requests.CreateUserRequest;
import com.techservices.usermanagement.models.requests.UpdateUserRequest;
import com.techservices.usermanagement.service.UserManagementService;
import com.techservices.usermanagement.validator.UserManagementValidator;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
public class UserManagementApi {

  @Autowired
  private UserManagementValidator userManagementValidator;
  @Autowired
  private UserManagementService userManagementService;

  @GetMapping("/{userId}")
  public ResponseEntity<UserDetails> getUserById(@PathVariable Long userId) {
    final UserDetails userDetailsResponse = userManagementService.getUserById(userId);
    return ResponseEntity.ok(userDetailsResponse);
  }

  @PostMapping
  public ResponseEntity<Void> createUser(@Valid @RequestBody CreateUserRequest createRequest) {
    userManagementValidator.validateCreateRequest(createRequest);
    final Long userCreatedResponse = userManagementService.createUser(createRequest);
    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userCreatedResponse).toUri();
    return ResponseEntity.created(location).build();
  }

  @PutMapping("/{userId}")
  public ResponseEntity<Void> updateUser(@Valid @RequestBody UpdateUserRequest updateRequest,
      @PathVariable Long userId) {
    userManagementValidator.validateUserUpdateRequest(updateRequest, userId);
    userManagementService.updateUser(updateRequest, userId);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{userId}")
  public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
    userManagementService.deleteUser(userId);
    return ResponseEntity.noContent().build();
  }

}
