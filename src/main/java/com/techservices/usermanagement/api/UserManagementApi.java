package com.techservices.usermanagement.api;

import com.techservices.usermanagement.models.reguests.UserRequest;
import com.techservices.usermanagement.models.responses.UserCreatedResponse;
import com.techservices.usermanagement.models.responses.UserDetailsResponse;
import com.techservices.usermanagement.models.responses.UserUpdateResponse;
import com.techservices.usermanagement.service.UserManagementService;
import com.techservices.usermanagement.validator.UserManagementValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserManagementApi {

    @Autowired
    private UserManagementValidator userManagementValidator;
    @Autowired
    private UserManagementService userManagementService;

    @GetMapping("/{userId}")
    public ResponseEntity<UserDetailsResponse> getUserById(@PathVariable Long userId) {
        final UserDetailsResponse userDetailsResponse = userManagementService.getUserById(userId);
        return ResponseEntity.ok(userDetailsResponse);
    }

    @PostMapping
    public ResponseEntity<UserCreatedResponse> createUser(@Valid @RequestBody UserRequest createRequest) {
        userManagementValidator.validateCreateRequest(createRequest);
        final UserCreatedResponse userCreatedResponse = userManagementService.createUser(createRequest);
        return ResponseEntity.ok(userCreatedResponse);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserUpdateResponse> updateUser(@Valid @RequestBody UserRequest updateRequest,
                                                         @PathVariable Long userId) {
        userManagementValidator.validateUserUpdateRequest(updateRequest, userId);
        final UserUpdateResponse userUpdateResponse = userManagementService.updateUser(updateRequest, userId);
        return ResponseEntity.ok(userUpdateResponse);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable Long userId) {
        userManagementService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

}
