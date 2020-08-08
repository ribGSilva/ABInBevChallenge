package br.com.gabriel.abambevchallenge.product.validators;

import br.com.gabriel.abambevchallenge.product.beans.dto.CreateProductDTO;
import br.com.gabriel.abambevchallenge.product.exceptions.ProductException;
import br.com.gabriel.abambevchallenge.product.utils.ExceptionEnum;
import br.com.gabriel.abambevchallenge.product.utils.ExceptionUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class ProductValidator {
    public void validate(CreateProductDTO createProductDTO) throws ProductException {

        Map<String, String> stringFields = new HashMap<String, String>() {{
            put("name", createProductDTO.getName());
            put("type", createProductDTO.getType());
            put("description", createProductDTO.getDescription());
        }};

        for (Map.Entry<String, String> item : stringFields.entrySet()) {
            if (Objects.isNull(item.getValue()) || item.getValue().isEmpty()) {
                throw ExceptionUtils.createException(ExceptionEnum.FIELD_REQUIRED, item.getKey());
            }
        }

        try {
            new BigDecimal(createProductDTO.getPrice());
        } catch (Exception e) {
            throw ExceptionUtils.createException(ExceptionEnum.FIELD_INVALID, "price");
        }
    }
}
