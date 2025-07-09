package pe.edu.certus.authmodule.logic.adapters.driver;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/marketplace/auth")
public class OAuth2Adapter {

    @GetMapping("/oauth-success")
    public String oauthSuccess() {
        return "/auth/login/templates/oauth2_success";
    }
}