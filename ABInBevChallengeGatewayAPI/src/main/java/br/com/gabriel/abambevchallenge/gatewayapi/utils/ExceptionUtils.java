package br.com.gabriel.abambevchallenge.gatewayapi.utils;

import br.com.gabriel.abambevchallenge.gatewayapi.exceptions.GatewayApiException;
import org.json.JSONObject;

import java.util.Objects;

public class ExceptionUtils {

    public static GatewayApiException createException(ExceptionEnum exceptionEnum, String... params) {
        GatewayApiException productException;

        if (Objects.isNull(params) || params.length == 0) {
            productException = new GatewayApiException(exceptionEnum.getCode(), exceptionEnum.getMessage());
        } else {
            final String formattedMessage = String.format(exceptionEnum.getMessage(), (Object[]) params);
            productException = new GatewayApiException(exceptionEnum.getCode(), formattedMessage);
        }

        return productException;
    }

    public static GatewayApiException createException(JSONObject error) {
        return new GatewayApiException(error.getString("code"), error.getString("message"));
    }
}
