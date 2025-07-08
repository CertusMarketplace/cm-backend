package pe.edu.certus.uimodule.ui.pages.profile.adapters.drivers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.edu.certus.uimodule.ui.pages.constants.BusinessRoute;
import pe.edu.certus.uimodule.ui.pages.profile.ports.drivers.ForProfile;

@Controller
<<<<<<< Updated upstream
@RequestMapping( BusinessRoute.BUSINESS_PAGE_ROUTE )
public class ProfileController implements ForProfile {

    @GetMapping( ProfileRoute.PROFILE_PAGE_ROUTE )
=======
@RequestMapping( BusinessRoute.BUSINESS_PAGE_ROUTE)
public class ProfileController implements ForProfile {

    @GetMapping( ProfileRoute.PROFILE_PAGE_ROUTE)
>>>>>>> Stashed changes
    @Override
    public String showProfile( ) {
        return ProfileRoute.PROFILE_PAGE_FILE;
    }
}
