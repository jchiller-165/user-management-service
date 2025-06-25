package com.techservices.usermanagement.repository.impl;

import com.techservices.usermanagement.repository.UserManagementRepository;
import com.techservices.usermanagement.repository.entity.UserDetailsEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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
  public boolean updateUser(@NonNull UserDetailsEntity userDetailsEntity) {
    UserDetailsEntity merged = entityManager.merge(userDetailsEntity);
    return merged != null;
  }

  @Override
  public boolean deleteUser(@NonNull Long userId) {
    UserDetailsEntity entity = entityManager.find(UserDetailsEntity.class, userId);
    if (entity != null) {
      entityManager.remove(entity);
      return true;
    }
    return false;
  }
}
