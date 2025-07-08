package pe.edu.certus.uimodule.ui.pages.works.adapters.drivers;

<<<<<<< Updated upstream
<<<<<<< Updated upstream
<<<<<<< Updated upstream
=======
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
>>>>>>> Stashed changes
=======
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
>>>>>>> Stashed changes
=======
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
>>>>>>> Stashed changes
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
<<<<<<< Updated upstream
<<<<<<< Updated upstream
<<<<<<< Updated upstream
import pe.edu.certus.uimodule.ui.pages.constants.BusinessRoute;
import pe.edu.certus.worksmodule.logic.model.WorkModel;
import pe.edu.certus.worksmodule.logic.ports.driver.ForWork;

@Controller
@RequestMapping( BusinessRoute.BUSINESS_PAGE_ROUTE )
=======
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
import org.springframework.web.bind.annotation.RequestParam;
import pe.edu.certus.uimodule.ui.pages.constants.BusinessRoute;
import pe.edu.certus.ratingsmodule.logic.model.RatingModel;
import pe.edu.certus.ratingsmodule.logic.ports.driver.ForRating;
import pe.edu.certus.worksmodule.logic.model.WorkModel;
import pe.edu.certus.worksmodule.logic.ports.driver.ForWork;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(BusinessRoute.BUSINESS_PAGE_ROUTE)
<<<<<<< Updated upstream
<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
@SuppressWarnings( "SpringJavaInjectionPointsAutowiringInspection" )
public class WorkRouteController {

    private final ForWork forWork;
<<<<<<< Updated upstream
<<<<<<< Updated upstream
<<<<<<< Updated upstream

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
=======
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
    private final ForRating forRating;

    public WorkRouteController( ForWork forWork, ForRating forRating ) {
        this.forWork = forWork;
        this.forRating = forRating;
    }


    @GetMapping(WorkRoute.WORK_PAGE_ROUTE)
    public String showWorksPage() {
        return WorkRoute.WORK_PAGE_FILE;
    }

    @GetMapping(WorkRoute.WORK_DETAIL_PAGE_ROUTE)
    public String showWorkDetailPage(@PathVariable Long workId, Model model) {
        WorkModel work = forWork.findWorkById(workId);
        List<RatingModel> allRatings = forRating.findAllRatings();
        List<RatingModel> workRatings = allRatings.stream()
                .filter(rating -> workId.equals(rating.getWorkId()))
                .collect(Collectors.toList());

        model.addAttribute("work", work);
        model.addAttribute("ratings", workRatings);
<<<<<<< Updated upstream
<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes

        return WorkRoute.WORK_DETAIL_PAGE_FILE;
    }
}