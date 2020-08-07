package br.com.gabriel.abambevchallenge.product.beans.dto;

import java.io.Serializable;

public class IdDTO implements Serializable {
    private static final long serialVersionUID = 6720021558267501163L;

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
