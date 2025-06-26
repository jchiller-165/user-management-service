package com.techservices.usermanagement.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.techservices.usermanagement.errors.exceptions.BadRequestException;

class BadRequestExceptionTest {

  @Test
  void testConstructorWithMessage() {
    BadRequestException ex = new BadRequestException("Bad Request");
    assertEquals("Bad Request", ex.getMessage());
  }

}
