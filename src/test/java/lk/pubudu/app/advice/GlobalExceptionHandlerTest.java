package lk.pubudu.app.advice;

import lk.pubudu.app.exception.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.MethodParameter;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GlobalExceptionHandlerTest {

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    @Test
    void duplicateEntityExceptionHandler() {
        assertEquals("Duplicate entity exception test", globalExceptionHandler.duplicateEntityExceptionHandler(new DuplicateKeyException("Duplicate entity exception test")).get("message"));
    }

    @Test
    void notFoundExceptionHandler() {
        assertEquals("Not found exception test", globalExceptionHandler.notFoundExceptionHandler(new NotFoundException("Not found exception test")).get("message"));
    }

    @Test
    void notAvailableExceptionHandler() {
        assertEquals("Not available exception test", globalExceptionHandler.notAvailableExceptionHandler(new NotAvailableException("Not available exception test")).get("message"));
    }

    @Test
    void alreadyIssuedExceptionHandler() {
        assertEquals("Already issued exception test", globalExceptionHandler.alreadyIssuedExceptionHandler(new AlreadyIssuedException("Already issued exception test")).get("message"));
    }

    @Test
    void limitExceedExceptionHandler() {
        assertEquals("Limit exceed exception test", globalExceptionHandler.limitExceedExceptionHandler(new LimitExceedException("Limit exceed exception test")).get("message"));
    }

    @Test
    void alreadyReturnExceptionHandler() {
        assertEquals("Already return exception test", globalExceptionHandler.alreadyReturnExceptionHandler(new AlreadyReturnedException("Already return exception test")).get("message"));
    }

    @Test
    void constraintViolationExceptionHandler() {
        assertEquals("Constraint violation exception test", globalExceptionHandler.constraintViolationExceptionHandler(new ConstraintViolationException("Constraint violation exception test")).get("message"));
    }
}