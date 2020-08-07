package br.com.gabriel.abambevchallenge.product.exceptions;

public class ProductException extends Exception {
    private static final long serialVersionUID = 5178928709890079070L;
    private String code;
    private String message;

    public ProductException(String code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
