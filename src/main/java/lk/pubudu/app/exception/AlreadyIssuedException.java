package lk.pubudu.app.exception;

import java.io.Serial;

public class AlreadyIssuedException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -6529826703798696068L;

    public AlreadyIssuedException(String message) {
        super(message);
    }

    public AlreadyIssuedException(String message, Throwable cause) {
        super(message, cause);
    }
}
