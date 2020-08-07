package br.com.gabriel.abambevchallenge.gatewayapi.utils;

public enum ExceptionEnum {
    INTERNAL_ERROR("gatewayapi-001", "Internal error"),
    GRANT_TYPE_NOT_SUPPORTED("gatewayapi-002", "Grant type not supported");

    private final String code;
    private final String message;

    ExceptionEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
