package br.com.gabriel.abambevchallenge.authorization.exceptions;

public class AuthorizationException extends Exception {
    private static final long serialVersionUID = -4326782360792839304L;
    private String code;
    private String message;

    public AuthorizationException(String code, String message) {
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
