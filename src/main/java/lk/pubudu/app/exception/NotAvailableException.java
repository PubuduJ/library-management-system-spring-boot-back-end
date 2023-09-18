package lk.pubudu.app.exception;

import java.io.Serial;

public class NotAvailableException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 609602806887657259L;

    public NotAvailableException(String message) {
        super(message);
    }

    public NotAvailableException(String message, Throwable cause) {
        super(message, cause);
    }
}
