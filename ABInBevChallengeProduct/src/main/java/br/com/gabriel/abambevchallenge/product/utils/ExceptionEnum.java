package br.com.gabriel.abambevchallenge.product.utils;

public enum ExceptionEnum {
    NOT_FOUND("product-001", "Item not found"),
    FIELD_REQUIRED("product-002", "Field '%s' required"),
    FIELD_INVALID("product-003", "Field '%s' invalid");

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
