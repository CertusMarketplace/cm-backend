package pe.edu.certus.uimodule.ui.pages.dashboard.seller.adapters.driver;

import org.springframework.stereotype.Controller;
<<<<<<< Updated upstream
<<<<<<< Updated upstream
<<<<<<< Updated upstream
<<<<<<< Updated upstream
import org.springframework.ui.Model;
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.edu.certus.uimodule.ui.pages.constants.BusinessRoute;
import pe.edu.certus.uimodule.ui.pages.dashboard.seller.ports.driver.ForSellerDashboard;
<<<<<<< Updated upstream
<<<<<<< Updated upstream
<<<<<<< Updated upstream
<<<<<<< Updated upstream
import pe.edu.certus.worksmodule.logic.model.WorkCategoryModel;
import pe.edu.certus.worksmodule.logic.model.WorkModel;
import pe.edu.certus.worksmodule.logic.ports.driver.ForWorkCategory;

@Controller
@RequestMapping( BusinessRoute.BUSINESS_PAGE_ROUTE )
@SuppressWarnings( "SpringJavaInjectionPointsAutowiringInspection" )
public class SellerDashboardController implements ForSellerDashboard {

    private final ForWorkCategory forWorkCategory;

    public SellerDashboardController( ForWorkCategory forWorkCategory ) {
        this.forWorkCategory = forWorkCategory;
    }

    @GetMapping( SellerDashboardRoute.SELLER_DASHBOARD_ROUTE )
    @Override
    public String showSellerDashboard( Model model ) { // <-- Añadir Model
        // Pasar un objeto vacío para el formulario del modal
        model.addAttribute( "newWork", WorkModel.builder( ).build( ) );

        // Pasar la lista de categorías para el select
        java.util.List< WorkCategoryModel > categories = forWorkCategory.findAllCategories( );
        model.addAttribute( "categories", categories );

        return SellerDashboardRoute.SELLER_DASHBOARD_FILE;
    }

    @GetMapping( "/fragments/seller-works" )
    public String loadWorkString( ) {
        return SellerDashboardRoute.SELLER_WORKS_FRAGMENT + " :: content";
    }

    @GetMapping( "/fragments/seller-works-under-review" )
    public String loadSellerWorksUnderReview( ) {
        return SellerDashboardRoute.SELLER_WORKS_UNDER_REVIEW_FRAGMENT + " :: content";
    }

    @GetMapping( "/fragments/seller-sales" )
    public String loadSales( ) {
=======
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes

@Controller
@RequestMapping( BusinessRoute.BUSINESS_PAGE_ROUTE)
public class SellerDashboardController implements ForSellerDashboard {

    @GetMapping(SellerDashboardRoute.SELLER_DASHBOARD_ROUTE)
    @Override
    public String showSellerDashboard( ) {
        return SellerDashboardRoute.SELLER_DASHBOARD_FILE;
    }

    @GetMapping("/fragments/seller-works")
    public String loadWorkString() {
        return SellerDashboardRoute.SELLER_WORKS_FRAGMENT + " :: content";
    }
    
    @GetMapping("/fragments/seller-works-under-review")
    public String loadSellerWorksUnderReview() {
        return SellerDashboardRoute.SELLER_WORKS_UNDER_REVIEW_FRAGMENT + " :: content";
    }

    @GetMapping("/fragments/seller-sales")
    public String loadSales() {
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
        return SellerDashboardRoute.SELLER_SALES_FRAGMENT + " :: content";
    }

}

