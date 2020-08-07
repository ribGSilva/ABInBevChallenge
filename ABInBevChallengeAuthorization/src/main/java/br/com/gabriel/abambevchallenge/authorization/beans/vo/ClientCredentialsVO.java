package br.com.gabriel.abambevchallenge.authorization.beans.vo;

import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "clientcredentials")
public class ClientCredentialsVO {

    @MongoId
    @Field
    private String clientId;
    @Field
    private String salt;
    @Field
    private String hash;

    @PersistenceConstructor
    public ClientCredentialsVO(String clientId, String salt, String hash) {
        this.clientId = clientId;
        this.salt = salt;
        this.hash = hash;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}
