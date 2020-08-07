package br.com.gabriel.abambevchallenge.gatewayapi.beans.dto;

import java.io.Serializable;

public class ErrorDTO implements Serializable {
    private static final long serialVersionUID = 6936250713831877009L;
    private String code;
    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
