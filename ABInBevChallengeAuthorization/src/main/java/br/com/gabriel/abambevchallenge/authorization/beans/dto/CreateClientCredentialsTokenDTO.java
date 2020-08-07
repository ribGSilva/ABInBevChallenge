package br.com.gabriel.abambevchallenge.authorization.beans.dto;

import java.io.Serializable;

public class CreateClientCredentialsTokenDTO implements Serializable {
    private static final long serialVersionUID = 3654398085651459310L;

    private String clientId;
    private String clientSecret;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }
}
