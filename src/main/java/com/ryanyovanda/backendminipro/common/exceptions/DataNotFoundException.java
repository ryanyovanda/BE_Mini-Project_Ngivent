package com.ryanyovanda.backendminipro.common.exceptions;

public class DataNotFoundException extends RuntimeException {
  public DataNotFoundException(String msg) {
    super(msg);
  }
}
