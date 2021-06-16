package com.example.empik.config;

import com.example.empik.exception.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@Slf4j
@ControllerAdvice
class GlobalControllerExceptionHandler {
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<?> handle(MethodArgumentNotValidException ex) {
    return new ResponseEntity<>(
        new ErrorResponse(ex.getBindingResult().getAllErrors().get(0).getDefaultMessage()),
        HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(Throwable.class)
  public ResponseEntity<?> handleThrowable(Throwable ex) {
    log.error("Exception in rest controller", ex);
    return new ResponseEntity<>(
        new ErrorResponse("Error occurred, we are working on that"),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
