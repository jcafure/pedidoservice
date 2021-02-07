package br.com.desafio.senior.api.pedido.handler;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Constraint;
import javax.validation.ConstraintViolationException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerAdviceExceptionHandler implements Serializable {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> validException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new LinkedHashMap<>();
        String mensagem = ex.getMessage();
        String timestamp = LocalDate.now().toString();
        errors.put(mensagem, timestamp);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errors);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> validRequestException(Exception ex) {
        Map<String, String> errors = new LinkedHashMap<>();
        String mensagem = ex.getMessage();
        String timestamp = LocalDate.now().toString();
        errors.put(mensagem, timestamp);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> constraintRequestException(Exception ex) {
        Map<String, String> errors = new LinkedHashMap<>();
        String mensagem = ex.getMessage();
        String timestamp = LocalDate.now().toString();
        errors.put(mensagem, timestamp);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

}
