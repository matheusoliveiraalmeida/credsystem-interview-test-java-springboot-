package br.com.credsystem.handler;

import br.com.credsystem.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Objects;

@ControllerAdvice
public class ControllerHandler {

    @ExceptionHandler({ ClientNotFoundException.class })
    public ResponseEntity<?> clientNotFound(ClientNotFoundException clientNotFoundException){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(clientNotFoundException.getMessage());
    }

    @ExceptionHandler({ CardNotFoundException.class })
    public ResponseEntity<?> cardNotFound(CardNotFoundException cardNotFoundException){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(cardNotFoundException.getMessage());
    }

    @ExceptionHandler({ ClientAlreadyExistsException.class })
    public ResponseEntity<?> clientAlreadyExists(ClientAlreadyExistsException clientAlreadyExistsException){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(clientAlreadyExistsException.getMessage());
    }

    @ExceptionHandler({ MethodArgumentNotValidException.class })
    public ResponseEntity<?> methodArgumentNotValid(MethodArgumentNotValidException methodArgumentNotValidException){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Objects.requireNonNull(methodArgumentNotValidException.getFieldError()).getDefaultMessage());
    }

    @ExceptionHandler({ PasswordNotMatchException.class })
    public ResponseEntity<?> passwordNotMatch(PasswordNotMatchException passwordNotMatchException){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(passwordNotMatchException.getMessage());
    }

    @ExceptionHandler({ InsufficientFundsException.class })
    public ResponseEntity<?> insufficientFounds(InsufficientFundsException insufficientFundsException){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(insufficientFundsException.getMessage());
    }

}