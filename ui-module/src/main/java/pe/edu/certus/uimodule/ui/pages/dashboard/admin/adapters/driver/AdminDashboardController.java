package pe.edu.certus.uimodule.ui.pages.dashboard.admin.adapters.driver;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.edu.certus.uimodule.ui.pages.constants.BusinessRoute;
import pe.edu.certus.uimodule.ui.pages.dashboard.admin.ports.driver.ForAdminDashboard;

@Controller
<<<<<<< Updated upstream
@RequestMapping( BusinessRoute.BUSINESS_PAGE_ROUTE )
public class AdminDashboardController implements ForAdminDashboard {

    @GetMapping( AdminDashboardRoute.ADMIN_DASHBOARD_ROUTE )
=======
@RequestMapping( BusinessRoute.BUSINESS_PAGE_ROUTE)
public class AdminDashboardController implements ForAdminDashboard {

    @GetMapping(AdminDashboardRoute.ADMIN_DASHBOARD_ROUTE )
>>>>>>> Stashed changes
    @Override
    public String showAdminDashboard( ) {
        return AdminDashboardRoute.ADMIN_DASHBOARD_FILE;
    }
}
