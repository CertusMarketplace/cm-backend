package pe.edu.certus.worksmodule.logic.adapters.driven;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pe.edu.certus.worksmodule.logic.model.WorkModel;
import pe.edu.certus.worksmodule.logic.ports.driven.ForManagingWork;
import pe.edu.certus.worksmodule.logic.ports.driver.ForWork;
<<<<<<< Updated upstream
<<<<<<< Updated upstream
<<<<<<< Updated upstream

=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
<<<<<<< Updated upstream
<<<<<<< Updated upstream
<<<<<<< Updated upstream
public class WorkManager implements ForWork <WorkModel, Long>{
=======
public class WorkManager implements ForWork {
>>>>>>> Stashed changes
=======
public class WorkManager implements ForWork {
>>>>>>> Stashed changes
=======
public class WorkManager implements ForWork {
>>>>>>> Stashed changes

    private final ForManagingWork forManagingWork;

    public WorkManager(ForManagingWork forManagingWork) {
        this.forManagingWork = forManagingWork;
    }

    @Override
<<<<<<< Updated upstream
<<<<<<< Updated upstream
<<<<<<< Updated upstream
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
=======
    public List<WorkModel> findWorksBySellerId(Long sellerId) {
        return forManagingWork.satisfyFindWorksBySellerId(sellerId);
    }

    @Override
    public Page<WorkModel> findAllWorks(String category, String priceRange, Integer popularity, Pageable pageable) {
        List<WorkModel> allWorks = forManagingWork.satisfyFindAllWorkWithRatings();
        List<WorkModel> filteredWorks = allWorks.stream()
                .filter(work -> {
                    if ("todos".equalsIgnoreCase(category) || category == null) {
                        return true;
                    }
                    if (work.getWorkCategory() == null) {
                        return false;
                    }
                    String workCategorySlug = work.getWorkCategory().toLowerCase().replace(" ", "-").replace("&", "y");
                    return category.equalsIgnoreCase(workCategorySlug);
                })
                .filter(work -> {
                    if ("all".equalsIgnoreCase(priceRange) || priceRange == null) return true;
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
                })
                .filter(work -> popularity == null || popularity == 0 || work.getAverageRating() >= popularity)
                .collect(Collectors.toList());

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), filteredWorks.size());
        List<WorkModel> pageContent = (start <= end) ? filteredWorks.subList(start, end) : List.of();
        return new PageImpl<>(pageContent, pageable, filteredWorks.size());
    }

    @Override
=======
    public List<WorkModel> findWorksBySellerId(Long sellerId) {
        return forManagingWork.satisfyFindWorksBySellerId(sellerId);
    }

    @Override
    public Page<WorkModel> findAllWorks(String category, String priceRange, Integer popularity, Pageable pageable) {
        List<WorkModel> allWorks = forManagingWork.satisfyFindAllWorkWithRatings();
        List<WorkModel> filteredWorks = allWorks.stream()
                .filter(work -> {
                    if ("todos".equalsIgnoreCase(category) || category == null) {
                        return true;
                    }
                    if (work.getWorkCategory() == null) {
                        return false;
                    }
                    String workCategorySlug = work.getWorkCategory().toLowerCase().replace(" ", "-").replace("&", "y");
                    return category.equalsIgnoreCase(workCategorySlug);
                })
                .filter(work -> {
                    if ("all".equalsIgnoreCase(priceRange) || priceRange == null) return true;
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
                })
                .filter(work -> popularity == null || popularity == 0 || work.getAverageRating() >= popularity)
                .collect(Collectors.toList());

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), filteredWorks.size());
        List<WorkModel> pageContent = (start <= end) ? filteredWorks.subList(start, end) : List.of();
        return new PageImpl<>(pageContent, pageable, filteredWorks.size());
    }

    @Override
>>>>>>> Stashed changes
=======
    public List<WorkModel> findWorksBySellerId(Long sellerId) {
        return forManagingWork.satisfyFindWorksBySellerId(sellerId);
    }

    @Override
    public Page<WorkModel> findAllWorks(String category, String priceRange, Integer popularity, Pageable pageable) {
        List<WorkModel> allWorks = forManagingWork.satisfyFindAllWorkWithRatings();
        List<WorkModel> filteredWorks = allWorks.stream()
                .filter(work -> {
                    if ("todos".equalsIgnoreCase(category) || category == null) {
                        return true;
                    }
                    if (work.getWorkCategory() == null) {
                        return false;
                    }
                    String workCategorySlug = work.getWorkCategory().toLowerCase().replace(" ", "-").replace("&", "y");
                    return category.equalsIgnoreCase(workCategorySlug);
                })
                .filter(work -> {
                    if ("all".equalsIgnoreCase(priceRange) || priceRange == null) return true;
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
                })
                .filter(work -> popularity == null || popularity == 0 || work.getAverageRating() >= popularity)
                .collect(Collectors.toList());

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), filteredWorks.size());
        List<WorkModel> pageContent = (start <= end) ? filteredWorks.subList(start, end) : List.of();
        return new PageImpl<>(pageContent, pageable, filteredWorks.size());
    }

    @Override
>>>>>>> Stashed changes
    public void createWork(WorkModel workModel) {
        forManagingWork.satisfyCreateWork(workModel);
    }

    @Override
    public WorkModel findWorkById(Long id) {
        return forManagingWork.satisfyFindWorkById(id);
    }

    @Override
<<<<<<< Updated upstream
<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
    public WorkModel updateWork(WorkModel workModel) {
        return forManagingWork.satisfyUpdateWork(workModel);
    }

    @Override
    public void deleteWork(Long id) {
<<<<<<< Updated upstream
<<<<<<< Updated upstream
<<<<<<< Updated upstream
        forManagingWork.satisfyDeleteWorkById( id);
    }

    @Override
    public List<WorkModel> findAllBySellerId(Long sellerId) {
        return forManagingWork.satisfyFindAllBySellerId(sellerId);
=======
        forManagingWork.satisfyDeleteWorkById(id);
>>>>>>> Stashed changes
=======
        forManagingWork.satisfyDeleteWorkById(id);
>>>>>>> Stashed changes
=======
        forManagingWork.satisfyDeleteWorkById(id);
>>>>>>> Stashed changes
    }
}