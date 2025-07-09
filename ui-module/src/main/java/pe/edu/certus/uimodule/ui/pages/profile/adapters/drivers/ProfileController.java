package pe.edu.certus.uimodule.ui.pages.profile.adapters.drivers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.edu.certus.uimodule.ui.pages.constants.BusinessRoute;

@Controller
@RequestMapping(BusinessRoute.BUSINESS_PAGE_ROUTE)
public class ProfileController {

    @GetMapping(value = {"/profile", "/profiles/{userId}"})
    public String showProfile(@PathVariable(required = false) Long userId) {
        return ProfileRoute.PROFILE_PAGE_FILE;
    }
}