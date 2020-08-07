package br.com.gabriel.abambevchallenge.gatewayapi.controller;

import br.com.gabriel.abambevchallenge.gatewayapi.beans.dto.CreateClientCredentialsDTO;
import br.com.gabriel.abambevchallenge.gatewayapi.beans.dto.ErrorDTO;
import br.com.gabriel.abambevchallenge.gatewayapi.beans.to.ClientCredentialsTO;
import br.com.gabriel.abambevchallenge.gatewayapi.beans.to.TokenTO;
import br.com.gabriel.abambevchallenge.gatewayapi.exceptions.GatewayApiException;
import br.com.gabriel.abambevchallenge.gatewayapi.service.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("oauth2")
public class OAuth2Controller {

    @Autowired
    private AuthorizationService authorizationService;

    @PostMapping("create")
    public ResponseEntity<?> create(@RequestBody CreateClientCredentialsDTO createClientCredentialsDTO) {
        ResponseEntity<?> responseEntity;
        try {
            ClientCredentialsTO clientCredentialsTO = authorizationService.createClient(createClientCredentialsDTO);
            responseEntity = new ResponseEntity<>(clientCredentialsTO, HttpStatus.OK);
        } catch (GatewayApiException e) {
            ErrorDTO errorDTO = new ErrorDTO() {{
                setCode(e.getCode());
                setMessage(e.getMessage());
            }};
            responseEntity = new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @PostMapping(path = "token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<?> generate(@RequestParam("client_id") String clientId,
                                      @RequestParam("client_secret") String clientSecret,
                                      @RequestParam("grant_type") String grantType) {
        ResponseEntity<?> responseEntity;
        try {
            TokenTO token = authorizationService.createToken(grantType, clientId, clientSecret);
            responseEntity = new ResponseEntity<>(token, HttpStatus.OK);
        } catch (GatewayApiException e) {
            ErrorDTO errorDTO = new ErrorDTO() {{
                setCode(e.getCode());
                setMessage(e.getMessage());
            }};
            responseEntity = new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
