package br.com.gabriel.abambevchallenge.product.beans.vo;

import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "product")
public class ProductVO {

    @MongoId
    private String id;
    @Field
    private String name;
    @Field
    private String type;
    @Field
    private String price;
    @Field
    private String description;

    @PersistenceConstructor
    public ProductVO(String id, String name, String type, String price, String description) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
