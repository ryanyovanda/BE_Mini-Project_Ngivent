package com.adepuu.fezz4ubackend.common.exceptions;

public class DuplicateEmailException extends RuntimeException {
  public DuplicateEmailException(String msg) {
    super(msg);
  }
}
