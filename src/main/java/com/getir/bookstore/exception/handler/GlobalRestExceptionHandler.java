package com.getir.bookstore.exception.handler;

import com.getir.bookstore.common.AppException;
import com.getir.bookstore.model.AppError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ValidationException;
import java.util.List;

@ControllerAdvice
@Slf4j
public class GlobalRestExceptionHandler {

    @ExceptionHandler(value = AppException.class)
    public <T> T handleAppException(AppException e) {
        AppError appError = createApiError(HttpStatus.BAD_REQUEST, e.getMessage());
        return (T) new ResponseEntity<>(appError, appError.getHttpStatus());
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public <T> T handleNotSupportedMethodException(HttpRequestMethodNotSupportedException ex) {
        AppError appError = createApiError(HttpStatus.BAD_REQUEST, ex.getMessage());
        return (T) new ResponseEntity<>(appError, appError.getHttpStatus());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public <T> T handleBaseException(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        AppError appError = processFieldErrors(fieldErrors);
        return (T) new ResponseEntity<>(appError, appError.getHttpStatus());
    }

    @ExceptionHandler(value = ObjectOptimisticLockingFailureException.class)
    public <T> T handleOptimisticLockingFailureException(Exception e) {
        AppError appError = createApiError(HttpStatus.INTERNAL_SERVER_ERROR, "please try again!");
        appError.addMessage(e.getMessage());
        return (T) new ResponseEntity<>(appError, appError.getHttpStatus());
    }


    @ExceptionHandler(value = Exception.class)
    public <T> T handleBaseException(Exception e) {
        AppError apiError = createApiError(HttpStatus.INTERNAL_SERVER_ERROR, "An Error Occurred!");
        log.error("An Error Occurred:{}", e);
        return (T) new ResponseEntity<>(apiError, apiError.getHttpStatus());
    }

    @ExceptionHandler(value = ValidationException.class)
    public <T> T handleValidationException(Exception e) {
        AppError appError = createApiError(HttpStatus.BAD_REQUEST, e.getMessage());
        return (T) new ResponseEntity<>(appError, appError.getHttpStatus());
    }

    @ExceptionHandler({AuthenticationException.class})
    public ResponseEntity<Object> handleAuthenticationException(final AuthenticationException ex) {
        final AppError appError = createApiError(HttpStatus.UNAUTHORIZED);
        appError.addMessage(ex.getMessage());

        return new ResponseEntity<>(appError, appError.getHttpStatus());
    }


    private AppError processFieldErrors(List<FieldError> fieldErrors) {
        AppError appError = createApiError(HttpStatus.BAD_REQUEST);
        for (FieldError fieldError : fieldErrors) {
            appError.addMessage(fieldError.getDefaultMessage());
        }
        return appError;
    }

    private AppError createApiError(HttpStatus httpStatus) {
        AppError appError = new AppError();
        appError.setError(httpStatus.getReasonPhrase());
        appError.setHttpStatus(httpStatus);
        appError.setStatus(httpStatus.value());
        return appError;
    }

    private AppError createApiError(HttpStatus httpStatus, String message) {
        AppError appError = new AppError();
        appError.setError(httpStatus.getReasonPhrase());
        appError.addMessage(message);
        appError.setHttpStatus(httpStatus);
        appError.setStatus(httpStatus.value());
        return appError;
    }
}
