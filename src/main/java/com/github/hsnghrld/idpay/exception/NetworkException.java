package com.github.hsnghrld.idpay.exception;

import java.io.IOException;

public final class NetworkException extends IOException {
    /**
     * Constructs a {@link NetworkException} with an explanatory message.
     *
     * @param message Detail about the reason for the exception.
     */
    public NetworkException(final String message) {
        super(message);
    }

    /**
     * Constructs a {@link NetworkException} with an explanatory message and cause.
     *
     * @param message Detail about the reason for the exception.
     * @param cause   The cause.
     */
    public NetworkException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new {@link NetworkException} with the specified cause.
     *
     * @param cause The cause.
     */
    public NetworkException(final Throwable cause) {
        super(cause.getMessage(), cause);
    }
}
