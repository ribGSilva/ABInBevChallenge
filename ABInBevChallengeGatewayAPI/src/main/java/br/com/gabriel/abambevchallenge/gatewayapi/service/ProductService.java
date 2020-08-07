package br.com.gabriel.abambevchallenge.gatewayapi.service;

import br.com.gabriel.abambevchallenge.gatewayapi.beans.dto.CreateProductDTO;
import br.com.gabriel.abambevchallenge.gatewayapi.beans.dto.IdDTO;
import br.com.gabriel.abambevchallenge.gatewayapi.beans.to.ProductTO;
import br.com.gabriel.abambevchallenge.gatewayapi.exceptions.GatewayApiException;
import br.com.gabriel.abambevchallenge.gatewayapi.utils.ConfigConstants;
import br.com.gabriel.abambevchallenge.gatewayapi.utils.ExceptionEnum;
import br.com.gabriel.abambevchallenge.gatewayapi.utils.ExceptionUtils;
import br.com.gabriel.abambevchallenge.gatewayapi.utils.RestClientUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    public List<ProductTO> getAll(String orderBy, String order) throws GatewayApiException {
        List<ProductTO> productTOs;
        RestClientUtils restClientUtils = new RestClientUtils();
        String path = String.format("?orderBy=%s&order=%s", orderBy, order);
        String body = restClientUtils.get(ConfigConstants.PRODUCT_ADDRESS, path);
        HttpStatus status = restClientUtils.getStatus();

        if (HttpStatus.OK.equals(status)) {
            try {
                productTOs = new ObjectMapper().readValue(body, new TypeReference<List<ProductTO>>(){});
            } catch (Exception e) {
                e.printStackTrace();
                throw ExceptionUtils.createException(ExceptionEnum.INTERNAL_ERROR);
            }
        } else if (HttpStatus.BAD_REQUEST.equals(status)) {
            throw ExceptionUtils.createException(new JSONObject(body));
        } else {
            throw ExceptionUtils.createException(ExceptionEnum.INTERNAL_ERROR);
        }
        return productTOs;
    }

    public ProductTO findId(String id) throws GatewayApiException {
        ProductTO productTO;
        RestClientUtils restClientUtils = new RestClientUtils();
        String body = restClientUtils.get(ConfigConstants.PRODUCT_ADDRESS, id);
        HttpStatus status = restClientUtils.getStatus();

        if (HttpStatus.OK.equals(status)) {
            try {
                productTO = new ObjectMapper().readValue(body, ProductTO.class);
            } catch (Exception e) {
                e.printStackTrace();
                throw ExceptionUtils.createException(ExceptionEnum.INTERNAL_ERROR);
            }
        } else if (HttpStatus.BAD_REQUEST.equals(status)) {
            throw ExceptionUtils.createException(new JSONObject(body));
        } else {
            throw ExceptionUtils.createException(ExceptionEnum.INTERNAL_ERROR);
        }
        return productTO;
    }

    public ProductTO findByName(String name) throws GatewayApiException {
        ProductTO productTO;
        RestClientUtils restClientUtils = new RestClientUtils();
        String body = restClientUtils.get(ConfigConstants.PRODUCT_ADDRESS, "byName/" + name);
        HttpStatus status = restClientUtils.getStatus();

        if (HttpStatus.OK.equals(status)) {
            try {
                productTO = new ObjectMapper().readValue(body, ProductTO.class);
            } catch (Exception e) {
                e.printStackTrace();
                throw ExceptionUtils.createException(ExceptionEnum.INTERNAL_ERROR);
            }
        } else if (HttpStatus.BAD_REQUEST.equals(status)) {
            throw ExceptionUtils.createException(new JSONObject(body));
        } else {
            throw ExceptionUtils.createException(ExceptionEnum.INTERNAL_ERROR);
        }
        return productTO;
    }

    public IdDTO create(CreateProductDTO createProductDTO) throws GatewayApiException {
        IdDTO idDTO;
        RestClientUtils restClientUtils = new RestClientUtils();
        String response = restClientUtils.post(ConfigConstants.PRODUCT_ADDRESS, "", new JSONObject(createProductDTO).toString());
        HttpStatus status = restClientUtils.getStatus();

        if (HttpStatus.OK.equals(status)) {
            try {
                idDTO = new ObjectMapper().readValue(response, IdDTO.class);
            } catch (Exception e) {
                e.printStackTrace();
                throw ExceptionUtils.createException(ExceptionEnum.INTERNAL_ERROR);
            }
        } else if (HttpStatus.BAD_REQUEST.equals(status)) {
            throw ExceptionUtils.createException(new JSONObject(response));
        } else {
            throw ExceptionUtils.createException(ExceptionEnum.INTERNAL_ERROR);
        }
        return idDTO;
    }

    public void update(String id, CreateProductDTO createProductDTO) throws GatewayApiException {
        RestClientUtils restClientUtils = new RestClientUtils();
        String response = restClientUtils.put(ConfigConstants.PRODUCT_ADDRESS, id, new JSONObject(createProductDTO).toString());
        HttpStatus status = restClientUtils.getStatus();

        if (HttpStatus.OK.equals(status)) {
        } else if (HttpStatus.BAD_REQUEST.equals(status)) {
            throw ExceptionUtils.createException(new JSONObject(response));
        } else {
            throw ExceptionUtils.createException(ExceptionEnum.INTERNAL_ERROR);
        }
    }

    public void delete(String id) throws GatewayApiException {
        RestClientUtils restClientUtils = new RestClientUtils();
        String response = restClientUtils.delete(ConfigConstants.PRODUCT_ADDRESS, id, null);
        HttpStatus status = restClientUtils.getStatus();

        if (HttpStatus.OK.equals(status)) {
        } else if (HttpStatus.BAD_REQUEST.equals(status)) {
            throw ExceptionUtils.createException(new JSONObject(response));
        } else {
            throw ExceptionUtils.createException(ExceptionEnum.INTERNAL_ERROR);
        }
    }
}
