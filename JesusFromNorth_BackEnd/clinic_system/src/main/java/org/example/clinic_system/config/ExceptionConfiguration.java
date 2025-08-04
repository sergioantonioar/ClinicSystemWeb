package org.example.clinic_system.config;

import org.example.clinic_system.dto.responseDTO.ErrorMessage;
import org.example.clinic_system.handler.BadRequestException;
import org.example.clinic_system.handler.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionConfiguration {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<?> notFoundException(NotFoundException e) {
        ErrorMessage errorMessage = new ErrorMessage(
                HttpStatus.NOT_FOUND,
                e.getMessage()
        );
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?>BadRequestException(BadRequestException e){
        ErrorMessage errorMessage = new ErrorMessage(
                HttpStatus.BAD_REQUEST,
                e.getMessage()
        );
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

}
