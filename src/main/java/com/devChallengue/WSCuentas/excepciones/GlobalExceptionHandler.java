package com.devChallengue.WSCuentas.excepciones;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.UUID;

@Slf4j
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
    public ResponseEntity<String> handleMovementNotFoundException(MovementNotFoundException mex) {
        return new ResponseEntity<>(mex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(feign.FeignException.NotFound.class)
    public ResponseEntity<String> handleFeignNotFound(feign.FeignException.NotFound ex) {
        // Si es un error 404 de cliente, maneja como ClientNotFoundException
        return new ResponseEntity<>("Error en consultar al WSClientes: " + ex.contentUTF8(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InsufficientFundsException.class)
    public ResponseEntity<String> handleInsufficientFundsException(InsufficientFundsException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenerixException(Exception ex) {
        // Generar un identificador único para rastrear el error en logs
        String errorId = UUID.randomUUID().toString();
        // Registrar el error en logs con el identificador
        log.error("Error inesperado - ID: {} - Mensaje: {}", errorId, ex.getMessage(), ex);

        // Devolver un mensaje con el identificador para que el cliente pueda reportarlo
        String errorMessage = "Ha ocurrido un error inesperado. Código de error: " + errorId;
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

