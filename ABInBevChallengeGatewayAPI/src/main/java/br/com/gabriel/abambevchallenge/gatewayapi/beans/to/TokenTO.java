package br.com.gabriel.abambevchallenge.gatewayapi.beans.to;

import java.io.Serializable;

public class TokenTO implements Serializable {
    private static final long serialVersionUID = -3343767547132121140L;

    private String token_type;
    private String access_token;
    private Long expires_in;

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public Long getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(Long expires_in) {
        this.expires_in = expires_in;
    }
}
