package lk.pubudu.app.exception;

import java.io.Serial;

public class LimitExceedException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -4339111787106739144L;

    public LimitExceedException(String message) {
        super(message);
    }

    public LimitExceedException(String message, Throwable cause) {
        super(message, cause);
    }
}
