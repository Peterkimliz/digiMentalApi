package com.example.digimental.advice;

import com.example.digimental.exceptions.ExceptionObject;
import com.example.digimental.exceptions.FoundException;
import com.example.digimental.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ContollerAdvice {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionObject> usernameNotFoundException(MethodArgumentNotValidException e){
        ExceptionObject exceptionObject=new ExceptionObject();
        e.getBindingResult().getAllErrors()
                .stream()
                .filter(FieldError.class::isInstance)
                .map(FieldError.class::cast)
                .forEach(fieldError -> exceptionObject.setMessage(fieldError.getDefaultMessage()));

        return  new ResponseEntity<ExceptionObject>(exceptionObject, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionObject> notFoundException(NotFoundException exception){
        ExceptionObject exceptionObject=new ExceptionObject();
        exceptionObject.setMessage(exception.getMessage());
        return new ResponseEntity<>(exceptionObject, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FoundException.class)
    public ResponseEntity<ExceptionObject> FoundException(FoundException exception){

        ExceptionObject exceptionObject=new ExceptionObject();
        exceptionObject.setMessage(exception.getMessage());
        return new ResponseEntity<>(exceptionObject, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ExceptionObject> FoundException(NullPointerException exception){
        System.out.println("called");
        ExceptionObject exceptionObject=new ExceptionObject();
        exceptionObject.setMessage(exception.getMessage());
        return new ResponseEntity<>(exceptionObject, HttpStatus.CONFLICT);
    }
}
