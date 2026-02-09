package com.jarvis.worksmartmoduelcompliancetracker.exceptionhandler;

import com.jarvis.worksmartmoduelcompliancetracker.exceptionhandler.exceptions.BadRequestException;
import com.jarvis.worksmartmoduelcompliancetracker.exceptionhandler.exceptions.InternalServerError;
import com.jarvis.worksmartmoduelcompliancetracker.exceptionhandler.exceptions.UnauthorizedException;
import com.jarvis.worksmartmoduelcompliancetracker.responsehandler.ResponseHandler;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * ResponseEntityExceptionHandler is a convenient base class provided by Spring Framework
     * (specifically in Spring MVC) to help you handle exceptions globally in a RESTful web application.
     */

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<Object> handleUnauthorized(UnauthorizedException ex, WebRequest request) {

        ResponseHandler<Object> response =
                new ResponseHandler<>(null, 401, "Unauthorized: " + ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }


    @ExceptionHandler(InternalServerError.class)
    public ResponseEntity<Object> handleInternalServerError(InternalServerError ex, WebRequest request) {

        ResponseHandler<Object> response =
                new ResponseHandler<>(null, 500, "Internal Server Error: " + ex.getMessage());

        return handleExceptionInternal(
                ex,
                response,
                new HttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                request
        );
    }


    @ExceptionHandler(BadRequestException.class)
    protected ResponseEntity<Object> handleMissingParameter(
            BadRequestException ex,
            WebRequest request) {

        String error = ex.getMessage();

        return new ResponseEntity<>(
                error,
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST
        );
    }

}
