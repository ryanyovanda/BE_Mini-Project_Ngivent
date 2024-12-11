package com.ryanyovanda.backendminipro.common.exceptions;

public class DuplicateEmailException extends RuntimeException {
  public DuplicateEmailException(String msg) {
    super(msg);
  }
}
