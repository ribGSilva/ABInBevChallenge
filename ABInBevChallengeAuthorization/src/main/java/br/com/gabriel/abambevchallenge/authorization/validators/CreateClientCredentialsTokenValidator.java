package br.com.gabriel.abambevchallenge.authorization.validators;

import br.com.gabriel.abambevchallenge.authorization.beans.dto.CreateClientCredentialsTokenDTO;
import br.com.gabriel.abambevchallenge.authorization.exceptions.AuthorizationException;
import br.com.gabriel.abambevchallenge.authorization.utils.ExceptionEnum;
import br.com.gabriel.abambevchallenge.authorization.utils.ExceptionUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class CreateClientCredentialsTokenValidator {
    public void validate(CreateClientCredentialsTokenDTO createClientCredentialsTokenDTO) throws AuthorizationException {

        Map<String, String> stringFields = new HashMap<String, String>() {{
            put("clientId", createClientCredentialsTokenDTO.getClientId());
            put("clientSecret", createClientCredentialsTokenDTO.getClientSecret());
        }};

        for (Map.Entry<String, String> item : stringFields.entrySet()) {
            if (Objects.isNull(item.getValue()) || item.getValue().isEmpty()) {
                throw ExceptionUtils.createException(ExceptionEnum.FIELD_REQUIRED, item.getKey());
            }
        }
    }
}
