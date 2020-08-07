package br.com.gabriel.abambevchallenge.gatewayapi.beans.dto;

import java.io.Serializable;

public class CreateClientCredentialsDTO implements Serializable {
    private static final long serialVersionUID = 1142824494047206128L;

    private String client_secret;

    public String getClient_secret() {
        return client_secret;
    }

    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret;
    }
}
