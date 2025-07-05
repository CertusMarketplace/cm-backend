package pe.edu.certus.authmodule.logic.ports.driver;

import java.util.HashMap;

public interface ForGoogleAuth {
    HashMap<String, String> loginWithGoogle(String idToken) throws Exception;
}