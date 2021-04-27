package com.galdovich.esm.exception.exception_handler;

import com.galdovich.esm.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final ExceptionMessageCreator creator;

    @Autowired
    public GlobalExceptionHandler(ExceptionMessageCreator creator) {
        this.creator = creator;
    }

    @ExceptionHandler(WrongParameterFormatException.class)
    public ResponseEntity<ExceptionResponse> handleResourceNotFoundException(WrongParameterFormatException e,
                                                                             WebRequest webRequest) {
        String message = creator.createMessage(e.getMessageKey());
        ExceptionResponse errorDetails = new ExceptionResponse(message, ExceptionCode.WRONG_PARAMETERS);
        return new ResponseEntity<>(errorDetails, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourcesNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ExceptionResponse> handleResourceNotFoundException(ResourcesNotFoundException e,
                                                                             WebRequest webRequest) {
        String message = creator.createMessage(e.getMessageKey(), e.getMessageParameter());
        ExceptionResponse errorDetails = new ExceptionResponse(message,
                ExceptionCode.RESOURCE_NOT_FOUND);
        return new ResponseEntity<>(errorDetails, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleAlreadyExistsException(AlreadyExistsException e,
                                                                          WebRequest webRequest) {
        String message = creator.createMessage(e.getMessageKey(), e.getMessageParameter());
        ExceptionResponse errorDetails = new ExceptionResponse(message,
                ExceptionCode.RESOURCE_EXISTS);
        return new ResponseEntity<>(errorDetails, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
