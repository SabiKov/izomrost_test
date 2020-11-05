package hu.sabikov.idomsoft.assignment.microservice1.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        CustomErrorDetails customErrorDetails =
                new CustomErrorDetails(
                        LocalDateTime.now(),
                        "From method argument not valid exception",
                        ex.getMessage());
        return new ResponseEntity<>(customErrorDetails, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        CustomErrorDetails customErrorDetails =
                new CustomErrorDetails(
                        LocalDateTime.now(),
                        "From HttpRequestMethodNoSupportedException - Method forbidden",
                        ex.getMessage());
        return new ResponseEntity<>(customErrorDetails, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(value = {InvalidRequestedParamsException.class})
    public final ResponseEntity<Object> handleInvalidRequestedParamsException(
            InvalidRequestedParamsException ue, WebRequest webRequest) {
        CustomErrorDetails customErrorDetails =
                new CustomErrorDetails(
                        LocalDateTime.now(),
                        ue.getMessage(),
                        webRequest.getDescription(false));
        return new ResponseEntity<>(customErrorDetails, HttpStatus.NOT_FOUND);
    }
}
