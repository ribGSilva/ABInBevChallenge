package br.com.gabriel.abambevchallenge.product.beans.dto;

import java.io.Serializable;

public class ErrorDTO implements Serializable {
    private static final long serialVersionUID = 8459658520138623555L;
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
