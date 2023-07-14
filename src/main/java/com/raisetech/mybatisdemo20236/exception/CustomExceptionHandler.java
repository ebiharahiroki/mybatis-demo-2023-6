package com.raisetech.mybatisdemo20236.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZonedDateTime;
import java.util.Map;

@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleNoResourceFound(
            ResourceNotFoundException e, HttpServletRequest request) {
        Map<String, String> body = Map.of(
                "timestamp", ZonedDateTime.now().toString(),
                "status", String.valueOf(HttpStatus.NOT_FOUND.value()),
                "error", HttpStatus.NOT_FOUND.getReasonPhrase(),
                "message", e.getMessage(),
                "path", request.getRequestURI());
        return new ResponseEntity(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
//    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException e, HttpServletRequest request) {
        Map<String, String> body = Map.of(
                "timestamp", ZonedDateTime.now().toString(),
                "status", String.valueOf(HttpStatus.BAD_REQUEST.value()),
                "error", HttpStatus.BAD_REQUEST.getReasonPhrase(),
                "message", e.getMessage(),
                "path", request.getRequestURI());
        return new ResponseEntity(body, HttpStatus.BAD_REQUEST);
    }
}
//            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
//        Map<String, String> body = Map.of(
//                "timestamp", ZonedDateTime.now().toString(),
//                "status", String.valueOf(status.value()),
//                "error", String.valueOf(headers.values()),
//                "message", ex.getMessage(),
//                "path", request.getContextPath());
//        return new ResponseEntity(body, HttpStatusCode.valueOf(status.value()));
//    }
//}
//@Override
//protected ResponseEntity<Object> handleMethodArgumentNotValid(
//        MethodArgumentNotValidException ex,
//        HttpHeaders headers,
//        HttpStatus status,
//        WebRequest request) {
//    List<String> errors = new ArrayList<String>();
//    for (FieldError error : ex.getBindingResult().getFieldErrors()) {
//        errors.add(error.getField() + ": " + error.getDefaultMessage());
//    }
//    for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
//        errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
//    }
//
//    ApiError apiError =
//            new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
//    return handleExceptionInternal(
//            ex, apiError, headers, apiError.getStatus(), request);
//}
