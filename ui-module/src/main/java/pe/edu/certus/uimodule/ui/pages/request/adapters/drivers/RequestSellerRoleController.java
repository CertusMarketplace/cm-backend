package pe.edu.certus.uimodule.ui.pages.request.adapters.drivers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.edu.certus.uimodule.ui.pages.constants.BusinessRoute;
import pe.edu.certus.uimodule.ui.pages.request.ports.drivers.ForRequestSeller;

@Controller
@RequestMapping( BusinessRoute.BUSINESS_PAGE_ROUTE )
public class RequestSellerRoleController implements ForRequestSeller {

    @GetMapping( RequestSellerRoleRoute.REQUEST_SELLER_ROLE_PAGE_ROUTE )
    @Override
    public String showRequestRolePage( ) {
        return RequestSellerRoleRoute.REQUEST_SELLER_ROLE_PAGE_FILE;
    }

}
