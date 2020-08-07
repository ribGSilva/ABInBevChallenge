package br.com.gabriel.abambevchallenge.authorization.controller;

import br.com.gabriel.abambevchallenge.authorization.beans.dto.CreateClientCredentialsDTO;
import br.com.gabriel.abambevchallenge.authorization.beans.dto.CreateClientCredentialsTokenDTO;
import br.com.gabriel.abambevchallenge.authorization.beans.dto.ErrorDTO;
import br.com.gabriel.abambevchallenge.authorization.beans.to.ClientCredentialsTO;
import br.com.gabriel.abambevchallenge.authorization.beans.to.TokenTO;
import br.com.gabriel.abambevchallenge.authorization.exceptions.AuthorizationException;
import br.com.gabriel.abambevchallenge.authorization.service.ClientCredentialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("clientcredentials")
public class OAuth2Controller {

    @Autowired
    private ClientCredentialsService clientCredentialsService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateClientCredentialsDTO createClientCredentialsDTO) {
        ResponseEntity<?> responseEntity;
        try {
            ClientCredentialsTO clientCredentialsTO = clientCredentialsService.createClient(createClientCredentialsDTO);
            responseEntity = new ResponseEntity<>(clientCredentialsTO, HttpStatus.OK);
        } catch (AuthorizationException e) {
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

    @PostMapping("token")
    public ResponseEntity<?> generate(@RequestBody CreateClientCredentialsTokenDTO createClientCredentialsTokenDTO) {
        ResponseEntity<?> responseEntity;
        try {
            TokenTO token = clientCredentialsService.createToken(createClientCredentialsTokenDTO);
            responseEntity = new ResponseEntity<>(token, HttpStatus.OK);
        } catch (AuthorizationException e) {
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

    @GetMapping("validate/{token}")
    public ResponseEntity<?> validate(@PathVariable("token") String token) {
        ResponseEntity<?> responseEntity;
        try {
            clientCredentialsService.validateToken(token);
            responseEntity = new ResponseEntity<>(HttpStatus.OK);
        } catch (AuthorizationException e) {
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
