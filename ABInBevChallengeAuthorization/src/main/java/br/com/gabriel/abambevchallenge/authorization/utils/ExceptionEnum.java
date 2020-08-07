package br.com.gabriel.abambevchallenge.authorization.utils;

public enum ExceptionEnum {
    NOT_FOUND("product-001", "Item not found"),
    FIELD_REQUIRED("product-002", "Field '%s' required"),
    INVALID_PASSWORD("product-003", "Invalid password"),
    WRONG_CREDENTIALS("product-004", "Wrong credentials"),
    TOKEN_EXPIRED("product-005", "Token expired"),
    INVALID_TOKEN("product-006", "Invalid token");

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
