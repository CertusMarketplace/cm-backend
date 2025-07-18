package pe.edu.certus.uimodule.ui.pages.auth.register.adapters.drivers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.edu.certus.uimodule.ui.pages.auth.register.ports.drivers.ForRegister;
import pe.edu.certus.uimodule.ui.pages.constants.BusinessRoute;

@Controller
@RequestMapping( BusinessRoute.BUSINESS_PAGE_ROUTE )
public class RegisterController implements ForRegister {

    @Value( "${google.client-id}" )
    private String googleClientId;

    @GetMapping( RegisterRoute.REGISTER_PAGE_ROUTE )
    @Override
    public String showRegisterPage( Model model ) {
        model.addAttribute( "googleClientId", googleClientId );
        return RegisterRoute.REGISTER_PAGE_FILE;
    }
}