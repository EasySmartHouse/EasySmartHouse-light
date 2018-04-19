package net.easysmarthouse.controller.advice;

import net.easysmarthouse.validation.ErrorDetails;
import net.easysmarthouse.validation.ErrorField;
import net.easysmarthouse.validation.RestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collection;
import java.util.Date;

@ControllerAdvice
@RestController
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RestException.class)
    public final ResponseEntity<ErrorDetails> handleRestException(RestException ex, WebRequest request) {
        Collection<ErrorField> errors = ex.getErrors();
        ErrorDetails errorDetails = new ErrorDetails(new Date(), errors, request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_ACCEPTABLE);
    }

}
