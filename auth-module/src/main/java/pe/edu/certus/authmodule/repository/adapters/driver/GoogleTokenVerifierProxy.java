package pe.edu.certus.authmodule.repository.adapters.driver;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pe.edu.certus.authmodule.logic.ports.driven.ForVerifyingGoogleToken;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

@Component
public class GoogleTokenVerifierProxy implements ForVerifyingGoogleToken {

    private final GoogleIdTokenVerifier verifier;


    public GoogleTokenVerifierProxy(@Value("${google.client-id}") String clientId) {
        this.verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), GsonFactory.getDefaultInstance())
                .setAudience(Collections.singletonList(clientId))
                .build();
    }

    @Override
    public GoogleIdToken.Payload verifyGoogleToken(String idTokenString) throws GeneralSecurityException, IOException {
        GoogleIdToken idToken = verifier.verify(idTokenString);
        if (idToken != null) {
            return idToken.getPayload();
        } else {
            throw new IllegalArgumentException("Invalid Google ID token.");
        }
    }
}