package br.com.gabriel.abambevchallenge.gatewayapi.beans.dto;

import java.io.Serializable;

public class CreateProductDTO implements Serializable {
    private static final long serialVersionUID = 6790003186309212988L;

    private String name;
    private String type;
    private String price;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
