package com.study.apiservicenews.web.controller;

import com.study.apiservicenews.exception.IncorrectClientIIdException;
import com.study.apiservicenews.exception.NotFoundEntityException;
import com.study.apiservicenews.web.model.exception.ExceptionResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ExceptionHandlerController {


    @ExceptionHandler(IncorrectClientIIdException.class)
    public ResponseEntity<ExceptionResponse> checkClientId(IncorrectClientIIdException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(ex.getLocalizedMessage()));
    }

    @ExceptionHandler(NotFoundEntityException.class)
    public ResponseEntity<ExceptionResponse> notFoundEntity(NotFoundEntityException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponse(ex.getLocalizedMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> methodValidParams(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();

        List<String> messages = bindingResult.getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();

        String exceptionMessage = String.join("; ", messages);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(exceptionMessage));
    }

}
