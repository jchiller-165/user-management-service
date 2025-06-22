package com.techservices.usermanagement.api;

import com.techservices.usermanagement.models.reguests.CreateUserRequest;
import com.techservices.usermanagement.models.reguests.UpdateUserRequest;
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
        return userDetailsResponse != null
                ? ResponseEntity.ok(userDetailsResponse)
                : ResponseEntity.notFound()
                .build();
    }

    @PostMapping
    public ResponseEntity<UserCreatedResponse> createUser(@Valid @RequestBody CreateUserRequest request) {
        if (userManagementValidator.validateCreateRequest(request)) {
            final UserCreatedResponse userCreatedResponse = userManagementService.createUser(request);
            return userCreatedResponse != null
                    ? ResponseEntity.ok(userCreatedResponse)
                    : ResponseEntity.badRequest()
                    .build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserUpdateResponse> updateUser(@Valid @RequestBody UpdateUserRequest request,
                                                         @PathVariable Long userId) {
        if (userManagementValidator.validateUserUpdateRequest(request, userId)) {
            final UserUpdateResponse userUpdateResponse = userManagementService.updateUser(request, userId);
            return userUpdateResponse != null
                    ? ResponseEntity.ok(userUpdateResponse)
                    : ResponseEntity.notFound()
                    .build();
        }
        return ResponseEntity.notFound()
                .build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable Long userId) {
        final Boolean isSuccess = userManagementService.deleteUser(userId);
        return isSuccess ? ResponseEntity.ok(true) : ResponseEntity.notFound()
                .build();
    }

}
