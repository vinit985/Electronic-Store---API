package com.electronic.store.exception;

import com.electronic.store.dtos.APIResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    //Handling resource not found exception
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<APIResponseMessage> resourceNotFoundExceptionHandler(ResourceNotFoundException ex)
    {
        logger.info("Error in resouce not found {}",ex.getMessage());
         return new  ResponseEntity(APIResponseMessage.builder()
             .message(ex.getMessage())
             .success(false)
             .httpStatus(HttpStatus.BAD_REQUEST).build(),HttpStatus.NOT_FOUND);
    }
    //Handline exception that comes because of wrong input from user
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,Object>> methodArgumentNotValidException(MethodArgumentNotValidException ex)
    {
       List<ObjectError> errorList = ex.getBindingResult().getAllErrors();
       Map<String,Object> map = new HashMap<>();
          errorList.stream().forEach(
               objectError -> {
                   //Helps us to get field name on which we are getting error
                   String fieldNameOnWhichErrorIsEncountered = ((FieldError)objectError).getField();
                   //Helps us to get msg that we have defined in our DT with @Notblank,@Size annotation*
                   String msgOnField = objectError.getDefaultMessage();
                   logger.info("Key and value  {} {}",fieldNameOnWhichErrorIsEncountered,msgOnField);
                   map.put(fieldNameOnWhichErrorIsEncountered,msgOnField);
               }
       );
          return new ResponseEntity<>(map,HttpStatus.NOT_FOUND);
    }
//     Handling bad API request
    @ExceptionHandler(IncorrectFileUploadException.class)
    public ResponseEntity<APIResponseMessage> incorrectFileUploadException(IncorrectFileUploadException ex)
    {
       return new ResponseEntity<APIResponseMessage>(APIResponseMessage.builder()
                .message(ex.getMessage())
                .success(false)
                .httpStatus(HttpStatus.BAD_REQUEST)
                .build(),HttpStatus.BAD_REQUEST);
    }
}
