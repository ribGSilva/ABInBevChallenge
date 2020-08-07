package br.com.gabriel.abambevchallenge.authorization.validators;

import br.com.gabriel.abambevchallenge.authorization.beans.dto.CreateClientCredentialsDTO;
import br.com.gabriel.abambevchallenge.authorization.beans.dto.CreateClientCredentialsTokenDTO;
import br.com.gabriel.abambevchallenge.authorization.exceptions.AuthorizationException;
import br.com.gabriel.abambevchallenge.authorization.utils.ExceptionEnum;
import br.com.gabriel.abambevchallenge.authorization.utils.ExceptionUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class CreateClientCredentialsValidator {

    public void validate(CreateClientCredentialsDTO createClientCredentialsDTO) throws AuthorizationException {
        Map<String, String> stringFields = new HashMap<String, String>() {{
            put("clientSecret", createClientCredentialsDTO.getClientSecret());
        }};

        for (Map.Entry<String, String> item : stringFields.entrySet()) {
            if (Objects.isNull(item.getValue()) || item.getValue().isEmpty()) {
                throw ExceptionUtils.createException(ExceptionEnum.FIELD_REQUIRED, item.getKey());
            }
        }
    }
}
