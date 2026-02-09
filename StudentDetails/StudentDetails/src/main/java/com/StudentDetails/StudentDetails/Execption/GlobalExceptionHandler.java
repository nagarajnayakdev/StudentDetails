package com.StudentDetails.StudentDetails.Execption;

//import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler( DuplicateStudentException.class)
      public ResponseEntity<ErrorResponse> handleDuplicateStudentException(DuplicateStudentException dsx, WebRequest webRequest ){
        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                dsx.getMessage(),
                webRequest.getDescription(false),
                "duplicate student found");
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler( StudentNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleStudentNotFoundException(StudentNotFoundException snf, WebRequest webRequest ){
        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                snf.getMessage(),
                webRequest.getDescription(false),
                " student not found");
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handledMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        Map<String,String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error->{
            errors.put(error.getField(),error.getDefaultMessage());
        });
        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);


    }
}
