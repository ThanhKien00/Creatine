package io.creatine.support;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.time.ZonedDateTime;

@Slf4j
@RestControllerAdvice
public class CreatineExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex,
                                                               HttpServletRequest request) {
        var fieldErrors = ex.getBindingResult().getFieldErrors();
        var errorsDetail = fieldErrors.stream()
                .map(error -> new ErrorResponse.ErrorDetail(error.getField(), error.getDefaultMessage()))
                .toList();
        return new ErrorResponse(
                "Some fields have been wrong",
                URI.create(request.getServletPath()),
                ZonedDateTime.now(),
                errorsDetail);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            DataIntegrityViolationException.class, IllegalArgumentException.class
    })
    public ErrorResponse handleDataIntegrityViolationException(RuntimeException ex, HttpServletRequest request) {
        log.info("**ApiExceptionHandler controller, handle API request*\n");
        return new ErrorResponse(ex.getMessage(), URI.create(request.getServletPath()), ZonedDateTime.now());
    }


}
