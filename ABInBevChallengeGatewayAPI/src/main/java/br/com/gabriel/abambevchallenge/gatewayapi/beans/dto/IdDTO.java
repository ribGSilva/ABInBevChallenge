package br.com.gabriel.abambevchallenge.gatewayapi.beans.dto;

import java.io.Serializable;

public class IdDTO implements Serializable {
    private static final long serialVersionUID = -2624967194177307188L;

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
