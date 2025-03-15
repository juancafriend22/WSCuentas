package com.devChallengue.WSCuentas.excepciones;

public class MovementNotFoundException extends RuntimeException {
  public MovementNotFoundException(String message) {
    super(message);
  }
}
