package site.telion.skyprocoursework02.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BigSizeAmountException extends RuntimeException {
    public BigSizeAmountException() {
        super();
    }

    public BigSizeAmountException(String message) {
        super(message);
    }

    public BigSizeAmountException(String message, Throwable cause) {
        super(message, cause);
    }

    public BigSizeAmountException(Throwable cause) {
        super(cause);
    }

    protected BigSizeAmountException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
