package pe.edu.certus.uimodule.ui.pages.works.adapters.drivers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.edu.certus.uimodule.ui.pages.constants.BusinessRoute;
import pe.edu.certus.worksmodule.logic.model.WorkModel;
import pe.edu.certus.worksmodule.logic.ports.driver.ForWork;

@Controller
@RequestMapping( BusinessRoute.BUSINESS_PAGE_ROUTE )
@SuppressWarnings( "SpringJavaInjectionPointsAutowiringInspection" )
public class WorkRouteController {

    private final ForWork forWork;

    public WorkRouteController( ForWork forWork ) {
        this.forWork = forWork;
    }

    @GetMapping( WorkRoute.WORK_PAGE_ROUTE )
    public String showWorksPage( ) {
        return WorkRoute.WORK_PAGE_FILE;
    }

    @GetMapping( WorkRoute.WORK_DETAIL_PAGE_ROUTE )
    public String showWorkDetailPage( @PathVariable Long workId, Model model ) {
        WorkModel work = ( WorkModel ) forWork.findWorkById( workId );

        model.addAttribute( "work", work );
        model.addAttribute( "ratings", work.getRatings( ) );

        return WorkRoute.WORK_DETAIL_PAGE_FILE;
    }
}