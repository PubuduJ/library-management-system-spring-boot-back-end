package lk.pubudu.app.exception;

import java.io.Serial;

public class ConstraintViolationException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 2617716194579190726L;

    public ConstraintViolationException(String message) {
        super(message);
    }

    public ConstraintViolationException(String message, Throwable cause) {
        super(message, cause);
    }
}
