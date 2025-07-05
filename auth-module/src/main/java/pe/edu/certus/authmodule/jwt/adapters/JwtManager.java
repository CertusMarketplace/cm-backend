package pe.edu.certus.authmodule.jwt.adapters;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import pe.edu.certus.authmodule.jwt.ports.ForJwt;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.text.ParseException;
import java.time.Instant;
import java.util.Date;

@Component
public class JwtManager implements ForJwt {

    @Value("classpath:jwt-keys/private-key.pem")
    private Resource privateKeyResource;

    @Value("classpath:jwt-keys/public-key.pem")
    private Resource publicKeyResource;

    private static final long EXPIRATION_TIME_MS = 3600_000; // 1 hora

    @Override
    public String generateJWT(Long subject) throws IOException, JOSEException {
        RSAPrivateKey privateKey = loadPrivateKey();

        JWSSigner signer = new RSASSASigner(privateKey);

        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject(subject.toString())
                .issuer("certus-marketplace")
                .issueTime(new Date())
                .expirationTime(new Date(Instant.now().toEpochMilli() + EXPIRATION_TIME_MS))
                .build();

        SignedJWT signedJWT = new SignedJWT(
                new JWSHeader.Builder(JWSAlgorithm.RS256).keyID("rsa-key-1").build(),
                claimsSet
        );

        signedJWT.sign(signer);

        return signedJWT.serialize();
    }

    @Override
    public JWTClaimsSet parseJWT(String jwtString) throws ParseException, JOSEException, IOException {
        RSAPublicKey publicKey = loadPublicKey();
        SignedJWT signedJWT = SignedJWT.parse(jwtString);

        JWSVerifier verifier = new RSASSAVerifier(publicKey);

        if (!signedJWT.verify(verifier)) {
            throw new JOSEException("Signature verification failed");
        }

        JWTClaimsSet claimsSet = signedJWT.getJWTClaimsSet();

        if (claimsSet.getExpirationTime().before(new Date())) {
            throw new JOSEException("Expired token");
        }

        return claimsSet;
    }


    private RSAPrivateKey loadPrivateKey() throws IOException {
        try {
            byte[] keyBytes = Files.readAllBytes(Paths.get(privateKeyResource.getURI()));
            String privateKeyPEM = new String(keyBytes, StandardCharsets.UTF_8)
                    .replace("-----BEGIN PRIVATE KEY-----", "")
                    .replaceAll(System.lineSeparator(), "")
                    .replace("-----END PRIVATE KEY-----", "");

            byte[] decoded = java.util.Base64.getDecoder().decode(privateKeyPEM);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decoded);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
        } catch (Exception e) {
            throw new IOException("Error loading private key", e);
        }
    }

    private RSAPublicKey loadPublicKey() throws IOException {
        try {
            byte[] keyBytes = Files.readAllBytes(Paths.get(publicKeyResource.getURI()));
            String publicKeyPEM = new String(keyBytes, StandardCharsets.UTF_8)
                    .replace("-----BEGIN PUBLIC KEY-----", "")
                    .replaceAll(System.lineSeparator(), "")
                    .replace("-----END PUBLIC KEY-----", "");

            byte[] decoded = java.util.Base64.getDecoder().decode(publicKeyPEM);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decoded);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return (RSAPublicKey) keyFactory.generatePublic(keySpec);
        } catch (Exception e) {
            throw new IOException("Error loading public key", e);
        }
    }
}