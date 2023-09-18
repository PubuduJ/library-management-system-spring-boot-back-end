package lk.pubudu.app.exception;

import java.io.Serial;

public class AlreadyReturnedException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -761428254117586490L;

    public AlreadyReturnedException(String message) {
        super(message);
    }

    public AlreadyReturnedException(String message, Throwable cause) {
        super(message, cause);
    }
}
