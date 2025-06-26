package com.techservices.usermanagement.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.techservices.usermanagement.errors.exceptions.NotFoundException;

class NotFoundExceptionTest {

  @Test
  void testConstructorWithMessage() {
    NotFoundException ex = new NotFoundException("User not found");
    assertEquals("User not found", ex.getMessage());
  }

}
