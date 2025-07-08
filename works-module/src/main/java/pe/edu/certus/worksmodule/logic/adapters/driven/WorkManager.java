package pe.edu.certus.worksmodule.logic.adapters.driven;

import org.springframework.stereotype.Service;
import pe.edu.certus.worksmodule.logic.model.WorkModel;
import pe.edu.certus.worksmodule.logic.ports.driven.ForManagingWork;
import pe.edu.certus.worksmodule.logic.ports.driver.ForWork;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkManager implements ForWork <WorkModel, Long>{

    private final ForManagingWork forManagingWork;

    public WorkManager(ForManagingWork forManagingWork) {
        this.forManagingWork = forManagingWork;
    }

    @Override
    public List<WorkModel> findWorksByIds(List<Long> ids) {
        return forManagingWork.satisfyFindWorksByIds(ids);
    }

    @Override
    public List<WorkModel> findAllWorks(String category, String priceRange, Integer popularity) {
        List<WorkModel> allWorks = forManagingWork.satisfyFindAllWorkWithRatings();

        return allWorks.stream()
                .filter(work -> filterByCategory(work, category))
                .filter(work -> filterByPriceRange(work, priceRange))
                .filter(work -> filterByPopularity(work, popularity))
                .collect(Collectors.toList());
    }


    private boolean filterByCategory(WorkModel work, String category) {
        if ("todos".equalsIgnoreCase(category) || category == null || category.isEmpty()) {
            return true;
        }
        if (work.getWorkCategory() == null) {
            return false;
        }
        String workCategorySlug = work.getWorkCategory().toLowerCase().replace(" ", "-").replace("&", "y");
        return category.equalsIgnoreCase(workCategorySlug);
    }

    private boolean filterByPriceRange(WorkModel work, String priceRange) {
        if ("all".equalsIgnoreCase(priceRange) || priceRange == null || priceRange.isEmpty()) {
            return true;
        }
        try {
            if (priceRange.endsWith("-plus")) {
                BigDecimal minPrice = new BigDecimal(priceRange.replace("-plus", ""));
                return work.getWorkPrice().compareTo(minPrice) >= 0;
            }
            String[] prices = priceRange.split("-");
            BigDecimal minPrice = new BigDecimal(prices[0]);
            BigDecimal maxPrice = new BigDecimal(prices[1]);
            return work.getWorkPrice().compareTo(minPrice) >= 0 && work.getWorkPrice().compareTo(maxPrice) <= 0;
        } catch (Exception e) {
            return true;
        }
    }

    private boolean filterByPopularity(WorkModel work, Integer popularity) {
        return popularity == null || popularity <= 0 || work.getAverageRating() >= popularity;
    }


    @Override
    public WorkModel createWork( WorkModel workModel ) {
        return forManagingWork.satisfyCreateWork( workModel );
    }

    @Override
    public WorkModel findWorkById(Long id) {
        return forManagingWork.satisfyFindWorkById(id);
    }

    @Override
    public WorkModel updateWork(WorkModel workModel) {
        return forManagingWork.satisfyUpdateWork(workModel);
    }

    @Override
    public void deleteWork(Long id) {
        forManagingWork.satisfyDeleteWorkById( id);
    }

    @Override
    public List<WorkModel> findAllBySellerId(Long sellerId) {
        return forManagingWork.satisfyFindAllBySellerId(sellerId);
    }
}