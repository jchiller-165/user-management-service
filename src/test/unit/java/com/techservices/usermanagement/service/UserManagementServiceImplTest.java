package com.techservices.usermanagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.techservices.usermanagement.TestModelsCreator;
import com.techservices.usermanagement.errors.exceptions.NotFoundException;
import com.techservices.usermanagement.models.UserDetails;
import com.techservices.usermanagement.models.requests.CreateUserRequest;
import com.techservices.usermanagement.repository.UserManagementRepository;
import com.techservices.usermanagement.repository.entity.UserDetailsEntity;
import com.techservices.usermanagement.service.impl.UserManagementServiceImpl;
import com.techservices.usermanagement.service.mappers.UserDetailsMapper;

class UserManagementServiceImplTest {

  @Mock
  private UserManagementRepository userRepository;
  @Mock
  private UserDetailsMapper userDetailsMapper;

  @InjectMocks
  private UserManagementServiceImpl userManagementService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void getUserById_returnsUserDetails_whenUserExists() {
    Long userId = 1L;
    UserDetailsEntity entity = new UserDetailsEntity();
    UserDetails userDetails = new UserDetails();

    when(userRepository.findUserById(userId)).thenReturn(Optional.of(entity));
    when(userDetailsMapper.toUserDetails(entity)).thenReturn(userDetails);

    UserDetails result = userManagementService.getUserById(userId);

    assertNotNull(result);
    assertEquals(userDetails, result);
    verify(userRepository).findUserById(userId);
    verify(userDetailsMapper).toUserDetails(entity);
  }

  @Test
  void getUserById_throwsNotFoundException_whenUserDoesNotExist() {
    Long userId = 2L;
    when(userRepository.findUserById(userId)).thenReturn(Optional.empty());

    assertThrows(NotFoundException.class, () -> userManagementService.getUserById(userId));
    verify(userRepository).findUserById(userId);
    verifyNoInteractions(userDetailsMapper);
  }

  @Test
  void createUser_returnsUserId_whenUserIsCreated() {
    CreateUserRequest request = TestModelsCreator.createUserCreateRequest();
    UserDetailsEntity entity = TestModelsCreator.createUserDetailsEntity();

    Long expectedUserId = 100L;

    when(userDetailsMapper.toUserDetailsEntity(request)).thenReturn(entity);
    when(userRepository.createUser(entity)).thenReturn(expectedUserId);

    Long result = userManagementService.createUser(request);

    assertNotNull(result);
    assertEquals(expectedUserId, result);
    verify(userDetailsMapper).toUserDetailsEntity(request);
    verify(userRepository).createUser(entity);
  }
}
