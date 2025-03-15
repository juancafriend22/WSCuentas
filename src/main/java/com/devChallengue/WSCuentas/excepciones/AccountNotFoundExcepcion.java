package com.devChallengue.WSCuentas.excepciones;

public class AccountNotFoundExcepcion extends RuntimeException {
  public AccountNotFoundExcepcion(String message) {
    super(message);
  }
}
