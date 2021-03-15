package com.github.hsnghrld.idpay.exception;

/**
 * Thrown when input parameters type do not match the expected type. Also for their structure and size.
 */
public final class ValidationException extends Exception {
    /**
     * Constructs a {@link ValidationException} with an explanatory message.
     *
     * @param message Detail about the reason for the exception.
     */
    public ValidationException(final String message) {
        super(message);
    }

    /**
     * Constructs a {@link ValidationException} with an explanatory message and cause.
     *
     * @param message Detail about the reason for the exception.
     * @param cause   The cause.
     */
    public ValidationException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new {@link ValidationException} with the specified cause.
     *
     * @param cause The cause.
     */
    public ValidationException(final Throwable cause) {
        super(cause.getMessage(), cause);
    }
}
