package br.com.gabriel.abambevchallenge.authorization.beans.to;

import java.io.Serializable;

public class TokenTO implements Serializable {
    private static final long serialVersionUID = -9117508937678658719L;

    private String token;
    private Long expires;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getExpires() {
        return expires;
    }

    public void setExpires(Long expires) {
        this.expires = expires;
    }
}
