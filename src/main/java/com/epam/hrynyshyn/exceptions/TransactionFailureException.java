package com.epam.hrynyshyn.exceptions;

/**
 * Used when some database connectivity problem acquired.
 */
public class TransactionFailureException extends RuntimeException {
    public TransactionFailureException() {
    }

    public TransactionFailureException(Throwable cause) {
        super(cause);
    }

    public TransactionFailureException(String message, Throwable cause) {
        super(message, cause);
    }
}
