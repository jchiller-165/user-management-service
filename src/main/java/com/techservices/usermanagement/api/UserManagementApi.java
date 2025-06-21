package com.techservices.usermanagement.api;

import com.techservices.usermanagement.api.models.reguests.CreateUserRequest;
import com.techservices.usermanagement.api.models.reguests.UpdateUserRequest;
import com.techservices.usermanagement.api.models.responses.UserDetailsResponse;
import com.techservices.usermanagement.api.models.responses.UserUpdateResponse;
import com.techservices.usermanagement.service.UserManagementService;
import com.techservices.usermanagement.validator.UserManagementValidator;
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
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<UserDetailsResponse> createUser(@RequestBody CreateUserRequest request) {
        if (!userManagementValidator.validateCreateRequest(request)) {
            final UserDetailsResponse userDetailsResponse = userManagementService.createUser(request);
            return ResponseEntity.ok(userDetailsResponse);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping
    public ResponseEntity<UserUpdateResponse> updateUser(@RequestBody UpdateUserRequest request,
                                                         @PathVariable Long userId) {
        if (!userManagementValidator.validateUserUpdateRequest(request, userId)) {
            final UserUpdateResponse userUpdateResponse = userManagementService.updateUser(request, userId);
            return ResponseEntity.ok(userUpdateResponse);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping
    public ResponseEntity<Boolean> deleteUser(@PathVariable Long userId) {
        final Boolean isSuccess = userManagementService.deleteUser(userId);
        return isSuccess ? ResponseEntity.ok(true) : ResponseEntity.notFound().build();
    }

}
