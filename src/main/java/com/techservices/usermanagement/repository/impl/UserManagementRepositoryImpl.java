package com.techservices.usermanagement.repository.impl;

import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.techservices.usermanagement.errors.exceptions.FailureToUpdateException;
import com.techservices.usermanagement.repository.UserManagementRepository;
import com.techservices.usermanagement.repository.entity.UserDetailsEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.NonNull;

@Repository
@Transactional
public class UserManagementRepositoryImpl implements UserManagementRepository {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public Optional<UserDetailsEntity> findUserById(@NonNull Long userId) {
    UserDetailsEntity entity = entityManager.find(UserDetailsEntity.class, userId);
    return Optional.ofNullable(entity);
  }

  @Override
  public Long createUser(@NonNull UserDetailsEntity userDetailsEntity) {
    entityManager.persist(userDetailsEntity);
    entityManager.flush();
    return userDetailsEntity.getId();
  }

  @Override
  public void updateUser(@NonNull UserDetailsEntity userDetailsEntity) {
    UserDetailsEntity merged = entityManager.merge(userDetailsEntity);
    if (merged != null) {
      entityManager.flush();
      return;
    }
    throw new FailureToUpdateException("Unable to update user details");
  }

  @Override
  public void deleteUser(@NonNull Long userId) {
    UserDetailsEntity entity = entityManager.find(UserDetailsEntity.class, userId);
    if (entity != null) {
      entityManager.remove(entity);
    }
  }
}
