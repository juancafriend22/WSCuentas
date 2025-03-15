package com.devChallengue.WSCuentas.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccountCreationException.class)
    public ResponseEntity<String> handleAccountCreationException(AccountCreationException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccountNotFoundExcepcion.class)
    public ResponseEntity<String> handleClientNotFoundException(AccountNotFoundExcepcion aex) {
        return new ResponseEntity<>(aex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MovementCreationException.class)
    public ResponseEntity<String> handleMovementCreationException(MovementCreationException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MovementNotFoundException.class)
    public ResponseEntity<String> handleMovementNotFoundException(AccountNotFoundExcepcion mex) {
        return new ResponseEntity<>(mex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenerixException(Exception ex) {
        return new ResponseEntity<>("Error en procesamiento de info", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

