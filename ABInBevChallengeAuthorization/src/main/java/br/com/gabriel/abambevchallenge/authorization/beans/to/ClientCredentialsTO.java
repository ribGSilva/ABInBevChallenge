package br.com.gabriel.abambevchallenge.authorization.beans.to;

import java.io.Serializable;

public class ClientCredentialsTO implements Serializable {
    private static final long serialVersionUID = 6548644864418589963L;

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
