package com.github.hsnghrld.idpay.exception;

import com.github.hsnghrld.idpay.ErrorCode;

import java.util.Arrays;

public final class ApiException extends Exception {
    private final String code;
    private final String message_en;
    private final String message_fa;

    /**
     * Constructs a {@link ApiException} with an explanatory message.
     *
     * @param code Detail about the reason for the exception.
     */
    public ApiException(final String code) {
        this.code = code;
        if (Arrays.asList(ErrorCode.ALL_ERROR_CODES).contains(code)) {
            this.message_en = ErrorCode.valueOf("_" + code).getDesc_en();
            this.message_fa = ErrorCode.valueOf("_" + code).getDesc_fa();
        } else {
            this.message_en = ErrorCode.valueOf("_0").getDesc_en().replace("{0}", code);
            this.message_fa = ErrorCode.valueOf("_0").getDesc_fa().replace("{0}", code);
        }
    }

    public String getCode() {
        return code;
    }

    public String getMessage_en() {
        return message_en;
    }

    public String getMessage_fa() {
        return message_fa;
    }

    @Override
    public String getMessage() {
        return getMessage_en();
    }
}
