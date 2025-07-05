package pe.edu.certus.authmodule.logic.ports.driven;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;

import java.io.IOException;
import java.security.GeneralSecurityException;

public interface ForVerifyingGoogleToken {
    GoogleIdToken.Payload verifyGoogleToken(String idTokenString) throws GeneralSecurityException, IOException;
}