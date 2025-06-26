package com.techservices.usermanagement.service.impl;

import static com.techservices.usermanagement.errors.exceptions.AppErrors.NOT_FOUND;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techservices.usermanagement.errors.exceptions.NotFoundException;
import com.techservices.usermanagement.models.UserDetails;
import com.techservices.usermanagement.models.requests.CreateUserRequest;
import com.techservices.usermanagement.models.requests.UpdateUserRequest;
import com.techservices.usermanagement.repository.UserManagementRepository;
import com.techservices.usermanagement.repository.entity.UserDetailsEntity;
import com.techservices.usermanagement.service.UserManagementService;
import com.techservices.usermanagement.service.mappers.UserDetailsMapper;

import lombok.NonNull;

@Service
public class UserManagementServiceImpl implements UserManagementService {

  private static final Logger LOGGER = LoggerFactory.getLogger(UserManagementServiceImpl.class);

  @Autowired
  private UserManagementRepository userRepository;
  @Autowired
  private UserDetailsMapper userDetailsMapper;

  @Override
  public UserDetails getUserById(@NonNull Long userId) {
    final UserDetailsEntity userDetailsEntity = userRepository.findUserById(userId)
        .orElseThrow(() -> new NotFoundException(NOT_FOUND));
    return userDetailsMapper.toUserDetails(userDetailsEntity);
  }

  @Override
  public Long createUser(@NonNull CreateUserRequest request) {
    final UserDetailsEntity userDetailsEntity = userDetailsMapper.toUserDetailsEntity(request);
    return userRepository.createUser(userDetailsEntity);
  }

  @Override
  public void updateUser(@NonNull UpdateUserRequest request, @NonNull Long userId) {
    final UserDetailsEntity userToUpdate = userRepository.findUserById(userId)
        .orElseThrow(() -> new NotFoundException(NOT_FOUND));
    userDetailsMapper.updateUserDetailsEntity(userToUpdate, request);
    userRepository.updateUser(userToUpdate);
  }

  @Override
  public void deleteUser(@NonNull Long userId) {
    final UserDetailsEntity userToDelete = userRepository.findUserById(userId)
        .orElseThrow(() -> new NotFoundException(NOT_FOUND));
    userRepository.deleteUser(userId);
  }

}
