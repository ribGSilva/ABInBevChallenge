package br.com.gabriel.abambevchallenge.authorization.beans.dto;

import java.io.Serializable;

public class CreateClientCredentialsDTO implements Serializable {
    private static final long serialVersionUID = 6813955371457630394L;

    private String clientSecret;

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }
}
