package pe.edu.certus.uimodule.ui.pages.auth.login.adapters.drivers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.edu.certus.uimodule.ui.pages.auth.login.ports.drivers.ForLogin;
import pe.edu.certus.uimodule.ui.pages.constants.BusinessRoute;

@Controller
@RequestMapping( BusinessRoute.BUSINESS_PAGE_ROUTE )
public class LoginController implements ForLogin {

    @Value( "${google.client-id}" )
    private String googleClientId;

    @GetMapping( LoginRoute.LOGIN_PAGE_ROUTE )
    @Override
    public String showLoginPage( Model model ) {
        model.addAttribute( "googleClientId", googleClientId );
        return LoginRoute.LOGIN_PAGE_FILE;
    }
}