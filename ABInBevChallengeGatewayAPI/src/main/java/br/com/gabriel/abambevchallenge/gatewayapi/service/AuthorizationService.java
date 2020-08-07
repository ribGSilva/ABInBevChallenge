package br.com.gabriel.abambevchallenge.gatewayapi.service;

import br.com.gabriel.abambevchallenge.gatewayapi.beans.dto.CreateClientCredentialsDTO;
import br.com.gabriel.abambevchallenge.gatewayapi.beans.to.ClientCredentialsTO;
import br.com.gabriel.abambevchallenge.gatewayapi.beans.to.TokenTO;
import br.com.gabriel.abambevchallenge.gatewayapi.exceptions.GatewayApiException;
import br.com.gabriel.abambevchallenge.gatewayapi.utils.ConfigConstants;
import br.com.gabriel.abambevchallenge.gatewayapi.utils.ExceptionEnum;
import br.com.gabriel.abambevchallenge.gatewayapi.utils.ExceptionUtils;
import br.com.gabriel.abambevchallenge.gatewayapi.utils.RestClientUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {

    public static final String CLIENT_CREDENTIALS_GRANT_TYPE = "client_credentials";

    public ClientCredentialsTO createClient(CreateClientCredentialsDTO createClientCredentialsDTO) throws GatewayApiException {
        ClientCredentialsTO clientCredentialsTO;
        try {
            RestClientUtils restClientUtils = new RestClientUtils();
            JSONObject bodyRequest = new JSONObject();
            bodyRequest.put("clientSecret", createClientCredentialsDTO.getClient_secret());
            String response = restClientUtils.post(ConfigConstants.AUTHORIZATION_ADDRESS, "clientcredentials/", bodyRequest.toString());
            HttpStatus status = restClientUtils.getStatus();

            if (HttpStatus.OK.equals(status)) {
                JSONObject responseJson = new JSONObject(response);
                clientCredentialsTO = new ClientCredentialsTO();
                clientCredentialsTO.setGrant_type(CLIENT_CREDENTIALS_GRANT_TYPE);
                clientCredentialsTO.setClient_id(responseJson.getString("clientId"));
                clientCredentialsTO.setClient_secret(responseJson.getString("clientSecret"));
            } else if (HttpStatus.BAD_REQUEST.equals(status)) {
                throw ExceptionUtils.createException(new JSONObject(response));
            } else {
                throw ExceptionUtils.createException(ExceptionEnum.INTERNAL_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw ExceptionUtils.createException(ExceptionEnum.INTERNAL_ERROR);
        }
        return clientCredentialsTO;
    }

    public TokenTO createToken(String grantType, String clientId, String clientSecret) throws GatewayApiException {
        TokenTO tokenTO;

        if (!CLIENT_CREDENTIALS_GRANT_TYPE.equals(grantType)) {
            throw ExceptionUtils.createException(ExceptionEnum.GRANT_TYPE_NOT_SUPPORTED);
        }
        try {
            RestClientUtils restClientUtils = new RestClientUtils();
            JSONObject requestJson = new JSONObject();
            requestJson.put("clientId", clientId);
            requestJson.put("clientSecret", clientSecret);
            String body = restClientUtils.post(ConfigConstants.AUTHORIZATION_ADDRESS, "clientcredentials/token", requestJson.toString());
            HttpStatus status = restClientUtils.getStatus();

            if (HttpStatus.OK.equals(status)) {
                tokenTO = new ObjectMapper().readValue(body, TokenTO.class);
            } else if (HttpStatus.BAD_REQUEST.equals(status)) {
                throw ExceptionUtils.createException(new JSONObject(body));
            } else {
                throw ExceptionUtils.createException(ExceptionEnum.INTERNAL_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw ExceptionUtils.createException(ExceptionEnum.INTERNAL_ERROR);
        }

        return tokenTO;
    }

    public void validateToken(String token) throws GatewayApiException {
        try {
            RestClientUtils restClientUtils = new RestClientUtils();
            String body = restClientUtils.get(ConfigConstants.AUTHORIZATION_ADDRESS, "validate/" + token);
            HttpStatus status = restClientUtils.getStatus();

            if (HttpStatus.OK.equals(status)) {
            } else if (HttpStatus.BAD_REQUEST.equals(status)) {
                throw ExceptionUtils.createException(new JSONObject(body));
            } else {
                throw ExceptionUtils.createException(ExceptionEnum.INTERNAL_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw ExceptionUtils.createException(ExceptionEnum.INTERNAL_ERROR);
        }
    }
}
