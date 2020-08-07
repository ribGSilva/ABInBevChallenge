package br.com.gabriel.abambevchallenge.authorization.utils;

public enum ExceptionEnum {
    NOT_FOUND("authorization-001", "Item not found"),
    FIELD_REQUIRED("authorization-002", "Field '%s' required"),
    INVALID_PASSWORD("authorization-003", "Invalid password"),
    WRONG_CREDENTIALS("authorization-004", "Wrong credentials"),
    TOKEN_EXPIRED("authorization-005", "Token expired"),
    INVALID_TOKEN("authorization-006", "Invalid token");

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
