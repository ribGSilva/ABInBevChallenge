package br.com.gabriel.abambevchallenge.authorization.utils;

import br.com.gabriel.abambevchallenge.authorization.exceptions.AuthorizationException;

import java.util.Objects;

public class ExceptionUtils {

    public static AuthorizationException createException(ExceptionEnum exceptionEnum, String... params) {
        AuthorizationException productException;

        if (Objects.isNull(params) || params.length == 0) {
            productException = new AuthorizationException(exceptionEnum.getCode(), exceptionEnum.getMessage());
        } else {
            final String formattedMessage = String.format(exceptionEnum.getMessage(), (Object[]) params);
            productException = new AuthorizationException(exceptionEnum.getCode(), formattedMessage);
        }

        return productException;
    }
}
