package pe.edu.certus.uimodule.ui.pages.home.adapters.drivers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.edu.certus.uimodule.ui.pages.constants.BusinessRoute;
import pe.edu.certus.uimodule.ui.pages.home.ports.drivers.ForHome;

@Controller
@RequestMapping( BusinessRoute.BUSINESS_PAGE_ROUTE )
public class HomeController implements ForHome {

<<<<<<< Updated upstream
    @GetMapping( HomeRoute.HOME_PAGE_ROUTE )
    @Override
    public String showHomePage( ) {
=======
    @GetMapping( HomeRoute.HOME_PAGE_ROUTE)
    @Override
    public String showHomePage() {
>>>>>>> Stashed changes
        return HomeRoute.HOME_PAGE_FILE;
    }
}
