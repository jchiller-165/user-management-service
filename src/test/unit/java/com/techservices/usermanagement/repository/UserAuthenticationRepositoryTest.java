package com.techservices.usermanagement.repository;

import static com.techservices.usermanagement.TestModelsCreator.USERNAME;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.techservices.usermanagement.TestModelsCreator;
import com.techservices.usermanagement.repository.entity.AppUserEntity;
import com.techservices.usermanagement.repository.impl.UserAuthenticationRepositoryImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

class UserAuthenticationRepositoryTest {

  @Mock
  private EntityManager entityManager;

  @Mock
  private TypedQuery<AppUserEntity> typedQuery;

  private UserAuthenticationRepositoryImpl repository;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    repository = new UserAuthenticationRepositoryImpl();
    // Inject mock EntityManager
    try {
      var field = UserAuthenticationRepositoryImpl.class.getDeclaredField("entityManager");
      field.setAccessible(true);
      field.set(repository, entityManager);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  void getUserByUsername_returnsUser() {
    String username = USERNAME;
    AppUserEntity user = TestModelsCreator.createAppUserEntity();
    user.setUsername(username);

    when(entityManager.createQuery(anyString(), eq(AppUserEntity.class))).thenReturn(typedQuery);
    when(typedQuery.setParameter(eq("username"), eq(username))).thenReturn(typedQuery);
    when(typedQuery.getResultStream()).thenReturn(List.of(user).stream());

    Optional<AppUserEntity> result = repository.getUserByUsername(username);

    assertTrue(result.isPresent());
    assertEquals(username, result.get().getUsername());
  }

  @Test
  void getUserByUsername_returnsEmpty() {
    String username = "nouser";

    when(entityManager.createQuery(anyString(), eq(AppUserEntity.class))).thenReturn(typedQuery);
    when(typedQuery.setParameter(eq("username"), eq(username))).thenReturn(typedQuery);
    when(typedQuery.getResultStream()).thenReturn(List.<AppUserEntity>of().stream());

    Optional<AppUserEntity> result = repository.getUserByUsername(username);

    assertFalse(result.isPresent());
  }

  @Test
  void saveUser_persistsAndReturnsId() {
    AppUserEntity user = TestModelsCreator.createAppUserEntity();

    doNothing().when(entityManager).persist(user);
    doNothing().when(entityManager).flush();

    Long id = repository.saveUser(user);

    verify(entityManager).persist(user);
    verify(entityManager).flush();
    assertEquals(user.getUserId(), id);
  }

}