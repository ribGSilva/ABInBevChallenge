package br.com.gabriel.abambevchallenge.gatewayapi.exceptions;

public class GatewayApiException extends Exception {
    private static final long serialVersionUID = 3894383291307303379L;
    private final String code;
    private final String message;

    public GatewayApiException(String code, String message) {
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
