package lk.pubudu.app.exception;

import java.io.Serial;

public class AlreadyReturnException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -761428254117586490L;

    public AlreadyReturnException(String message) {
        super(message);
    }

    public AlreadyReturnException(String message, Throwable cause) {
        super(message, cause);
    }
}
