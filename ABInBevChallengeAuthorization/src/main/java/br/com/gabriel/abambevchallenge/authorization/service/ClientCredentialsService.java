package br.com.gabriel.abambevchallenge.authorization.service;

import br.com.gabriel.abambevchallenge.authorization.beans.dto.CreateClientCredentialsDTO;
import br.com.gabriel.abambevchallenge.authorization.beans.dto.CreateClientCredentialsTokenDTO;
import br.com.gabriel.abambevchallenge.authorization.beans.to.ClientCredentialsTO;
import br.com.gabriel.abambevchallenge.authorization.beans.to.TokenTO;
import br.com.gabriel.abambevchallenge.authorization.beans.vo.ClientCredentialsVO;
import br.com.gabriel.abambevchallenge.authorization.exceptions.AuthorizationException;
import br.com.gabriel.abambevchallenge.authorization.repository.ClientCredentialsDAO;
import br.com.gabriel.abambevchallenge.authorization.utils.ConfigConstants;
import br.com.gabriel.abambevchallenge.authorization.utils.ExceptionEnum;
import br.com.gabriel.abambevchallenge.authorization.utils.ExceptionUtils;
import br.com.gabriel.abambevchallenge.authorization.validators.CreateClientCredentialsTokenValidator;
import br.com.gabriel.abambevchallenge.authorization.validators.CreateClientCredentialsValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClientCredentialsService {

    public static final String TOKEN_TYPE_BEARER = "Bearer";
    @Autowired
    private CreateClientCredentialsTokenValidator createClientCredentialsTokenValidator;

    @Autowired
    private CreateClientCredentialsValidator createClientCredentialsValidator;

    @Autowired
    private CryptographyService cryptographyService;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private ClientCredentialsDAO clientCredentialsDAO;

    public ClientCredentialsTO createClient(CreateClientCredentialsDTO createClientCredentialsDTO) throws AuthorizationException {
        ClientCredentialsTO clientCredentialsTO;
        createClientCredentialsValidator.validate(createClientCredentialsDTO);
        String clientId = UUID.randomUUID().toString();
        byte[] salt = cryptographyService.generateSalt(ConfigConstants.SEED_SALT);
        String hash = cryptographyService.generateHash(
                createClientCredentialsDTO.getClientSecret(),
                salt
        );
        ClientCredentialsVO clientCredentialsVO = new ClientCredentialsVO(
                clientId,
                Base64.getEncoder().encodeToString(salt),
                hash
        );
        clientCredentialsDAO.save(clientCredentialsVO);
        clientCredentialsTO = new ClientCredentialsTO() {{
            setClientId(clientId);
            setClientSecret(createClientCredentialsDTO.getClientSecret());
        }};
        return clientCredentialsTO;
    }

    public TokenTO createToken(CreateClientCredentialsTokenDTO createClientCredentialsTokenDTO) throws AuthorizationException {
        TokenTO tokenTO;
        createClientCredentialsTokenValidator.validate(createClientCredentialsTokenDTO);
        Optional<ClientCredentialsVO> optionalClientCredentialsVO = clientCredentialsDAO.findById(createClientCredentialsTokenDTO.getClientId());
        if (optionalClientCredentialsVO.isPresent()) {
            ClientCredentialsVO clientCredentialsVO = optionalClientCredentialsVO.get();
            String hash = cryptographyService.generateHash(
                    createClientCredentialsTokenDTO.getClientSecret(),
                    Base64.getDecoder().decode(clientCredentialsVO.getSalt()));
            if (!clientCredentialsVO.getHash().equals(hash)) {
                throw ExceptionUtils.createException(ExceptionEnum.WRONG_CREDENTIALS);
            }
            String token = jwtService.create(clientCredentialsVO.getClientId(), ConfigConstants.TOKEN_EXPIRATION_TIME);
            tokenTO = new TokenTO() {{
                setType(TOKEN_TYPE_BEARER);
                setToken(token);
                setExpires(ConfigConstants.TOKEN_EXPIRATION_TIME);
            }};
        } else {
            throw ExceptionUtils.createException(ExceptionEnum.NOT_FOUND);
        }
        return tokenTO;
    }

    public void validateToken(String token) throws AuthorizationException {
        jwtService.validate(token);
    }
}
