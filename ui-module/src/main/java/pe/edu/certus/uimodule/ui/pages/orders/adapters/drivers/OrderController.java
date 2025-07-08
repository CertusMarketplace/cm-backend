package pe.edu.certus.uimodule.ui.pages.orders.adapters.drivers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.edu.certus.uimodule.ui.pages.constants.BusinessRoute;
import pe.edu.certus.uimodule.ui.pages.orders.ports.drivers.ForOrder;

@Controller
<<<<<<< Updated upstream
<<<<<<< Updated upstream
<<<<<<< Updated upstream
<<<<<<< Updated upstream
@RequestMapping( BusinessRoute.BUSINESS_PAGE_ROUTE )
public class OrderController implements ForOrder {

    @GetMapping( OrderRoute.ORDERS_PAGE_ROUTE )
=======
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
@RequestMapping( BusinessRoute.BUSINESS_PAGE_ROUTE)
public class OrderController implements ForOrder {

    @GetMapping( OrderRoute.ORDERS_PAGE_ROUTE)
<<<<<<< Updated upstream
<<<<<<< Updated upstream
<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
    @Override
    public String showOrders( ) {
        return OrderRoute.ORDERS_PAGE_FILE;
    }


<<<<<<< Updated upstream
<<<<<<< Updated upstream
<<<<<<< Updated upstream
<<<<<<< Updated upstream
    @GetMapping( OrderRoute.ORDER_DETAILS_PAGE_ROUTE )
=======
    @GetMapping( OrderRoute.ORDER_DETAILS_PAGE_ROUTE)
>>>>>>> Stashed changes
=======
    @GetMapping( OrderRoute.ORDER_DETAILS_PAGE_ROUTE)
>>>>>>> Stashed changes
=======
    @GetMapping( OrderRoute.ORDER_DETAILS_PAGE_ROUTE)
>>>>>>> Stashed changes
=======
    @GetMapping( OrderRoute.ORDER_DETAILS_PAGE_ROUTE)
>>>>>>> Stashed changes
    @Override
    public String showOrderDetails( ) {
        return OrderRoute.ORDER_DETAILS_PAGE_FILE;
    }
}
