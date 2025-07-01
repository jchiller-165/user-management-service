package com.techservices.usermanagement.repository.impl;

import java.util.Optional;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import com.techservices.usermanagement.repository.UserAuthenticationRepository;
import com.techservices.usermanagement.repository.entity.AppUserEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class UserAuthenticationRepositoryImpl implements UserAuthenticationRepository {

  private static final String USERNAME_QUERY = "SELECT u FROM AppUserEntity u WHERE u.username = :username";

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public Optional<AppUserEntity> getUserByUsername(@NonNull String username) {
    return entityManager.createQuery(USERNAME_QUERY, AppUserEntity.class).setParameter("username", username)
        .getResultStream().findFirst();
  }

  @Override
  public Long saveUser(@NonNull AppUserEntity appUserEntity) {
    entityManager.persist(appUserEntity);
    entityManager.flush();
    return appUserEntity.getUserId();
  }
}
