package br.com.gabriel.abambevchallenge.authorization.service;

import br.com.gabriel.abambevchallenge.authorization.exceptions.AuthorizationException;
import br.com.gabriel.abambevchallenge.authorization.utils.ConfigConstants;
import br.com.gabriel.abambevchallenge.authorization.utils.ExceptionEnum;
import br.com.gabriel.abambevchallenge.authorization.utils.ExceptionUtils;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.Calendar;
import java.util.TimeZone;

@Service
public class JWTService {

    public String create(String subject, Long expirationTime) {
        String token = null;
        Calendar expiration = Calendar.getInstance(TimeZone.getTimeZone(ZoneId.of(ConfigConstants.TIME_ZONE)));
        expiration.add(Calendar.SECOND, expirationTime.intValue());
        token = Jwts.builder().setSubject(subject).setExpiration(expiration.getTime())
                .signWith(SignatureAlgorithm.HS512, ConfigConstants.TOKEN_MATER_KEY).compact();
        return token;
    }

    public void validate(String token) throws AuthorizationException {
        try {
            Jwts.parser().setSigningKey(ConfigConstants.TOKEN_MATER_KEY).parseClaimsJws(token);
        } catch (ExpiredJwtException e) {
            throw ExceptionUtils.createException(ExceptionEnum.TOKEN_EXPIRED);
        } catch (Exception e) {
            throw ExceptionUtils.createException(ExceptionEnum.INVALID_TOKEN);
        }
    }
}
