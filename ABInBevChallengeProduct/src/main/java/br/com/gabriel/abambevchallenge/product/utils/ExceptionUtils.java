package br.com.gabriel.abambevchallenge.product.utils;

import br.com.gabriel.abambevchallenge.product.exceptions.ProductException;

import java.util.Objects;


public class ExceptionUtils {

    public static ProductException createException(ExceptionEnum exceptionEnum, String... params) {
        ProductException productException;

        if (Objects.isNull(params) || params.length == 0) {
            productException = new ProductException(exceptionEnum.getCode(), exceptionEnum.getMessage());
        } else {
            final String formattedMessage = String.format(exceptionEnum.getMessage(), (Object[]) params);
            productException = new ProductException(exceptionEnum.getCode(), formattedMessage);
        }

        return productException;
    }
}
