package pe.edu.certus.authmodule.jwt.ports;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.JWTClaimsSet;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.ParseException;

public interface ForJwt {
    String generateJWT(Long subject) throws JOSEException, IOException, NoSuchAlgorithmException, InvalidKeySpecException;

    JWTClaimsSet parseJWT(String jwt) throws ParseException, JOSEException, IOException;
}