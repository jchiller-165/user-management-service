package com.techservices.usermanagement.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.techservices.usermanagement.errors.exceptions.FailureToUpdateException;
import com.techservices.usermanagement.repository.entity.UserDetailsEntity;
import com.techservices.usermanagement.repository.impl.UserManagementRepositoryImpl;

import jakarta.persistence.EntityManager;

class UserManagementRepositoryImplTest {
  private EntityManager entityManager;
  private UserManagementRepositoryImpl repository;

  @BeforeEach
  void setUp() {
    entityManager = mock(EntityManager.class);
    repository = new UserManagementRepositoryImpl();
    // Use reflection to inject the mock EntityManager
    try {
      var field = UserManagementRepositoryImpl.class.getDeclaredField("entityManager");
      field.setAccessible(true);
      field.set(repository, entityManager);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  void createUser_persistsAndReturnsId() {
    UserDetailsEntity entity = new UserDetailsEntity();
    entity.setId(42L);

    doNothing().when(entityManager).persist(entity);
    doNothing().when(entityManager).flush();

    Long id = repository.createUser(entity);

    verify(entityManager).persist(entity);
    verify(entityManager).flush();
    assertEquals(42L, id);
  }

  @Test
  void updateUser_mergesAndFlushes() {
    UserDetailsEntity entity = new UserDetailsEntity();
    when(entityManager.merge(entity)).thenReturn(entity);

    repository.updateUser(entity);

    verify(entityManager).merge(entity);
    verify(entityManager).flush();
  }

  @Test
  void updateUser_mergeReturnsNull_throwsException() {
    UserDetailsEntity entity = new UserDetailsEntity();
    when(entityManager.merge(entity)).thenReturn(null);

    assertThrows(FailureToUpdateException.class, () -> repository.updateUser(entity));
  }

  @Test
  void deleteUser_entityExists_removes() {
    UserDetailsEntity entity = new UserDetailsEntity();
    when(entityManager.find(UserDetailsEntity.class, 1L)).thenReturn(entity);

    repository.deleteUser(1L);

    verify(entityManager).remove(entity);
  }

  @Test
  void deleteUser_entityNotFound_doesNotRemove() {
    when(entityManager.find(UserDetailsEntity.class, 1L)).thenReturn(null);

    repository.deleteUser(1L);

    verify(entityManager, never()).remove(any());
  }

  @Test
  void findUserById_entityFound_returnsOptional() {
    UserDetailsEntity entity = new UserDetailsEntity();
    when(entityManager.find(UserDetailsEntity.class, 1L)).thenReturn(entity);

    Optional<UserDetailsEntity> result = repository.findUserById(1L);

    assertTrue(result.isPresent());
    assertEquals(entity, result.get());
  }

  @Test
  void findUserById_entityNotFound_returnsEmptyOptional() {
    when(entityManager.find(UserDetailsEntity.class, 1L)).thenReturn(null);

    Optional<UserDetailsEntity> result = repository.findUserById(1L);

    assertTrue(result.isEmpty());
  }
}
