package br.com.gabriel.abambevchallenge.authorization.service;

import br.com.gabriel.abambevchallenge.authorization.exceptions.AuthorizationException;
import br.com.gabriel.abambevchallenge.authorization.utils.ExceptionEnum;
import br.com.gabriel.abambevchallenge.authorization.utils.ExceptionUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Security;

@Service
public class CryptographyService {

    public static final String SHA_512 = "SHA-512";

    public static final String X = "%064x";

    public String generateHash(String text, byte[] salt) throws AuthorizationException {
        String hex = null;
        try {
            Security.addProvider(new BouncyCastleProvider());
            MessageDigest messageDigest = MessageDigest.getInstance(SHA_512);
            messageDigest.update(salt);
            byte[] hashed = messageDigest.digest(text.getBytes());
            hex = String.format(X, new BigInteger(1, hashed));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw ExceptionUtils.createException(ExceptionEnum.INVALID_PASSWORD);
        }
        return hex;
    }

    public byte[] generateSalt(Integer seedSalt) {
        byte[] salt;
        SecureRandom rng = new SecureRandom();
        salt = rng.generateSeed(seedSalt);
        return salt;
    }
}
