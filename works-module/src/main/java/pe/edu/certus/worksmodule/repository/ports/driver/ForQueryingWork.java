package pe.edu.certus.worksmodule.repository.ports.driver;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.certus.worksmodule.repository.entity.WorkEntity;
import java.util.List;
<<<<<<< Updated upstream
<<<<<<< Updated upstream

import java.util.List;

@Repository
public interface ForQueryingWork extends JpaRepository<WorkEntity, Long> {
<<<<<<< Updated upstream

    @Query("SELECT DISTINCT w FROM WorkEntity w " +
            "LEFT JOIN FETCH w.workCategory " +
            "LEFT JOIN FETCH w.seller " +
            "LEFT JOIN FETCH w.ratings")
    List<WorkEntity> findAllWithCategoryAndRatings();

    @Query("SELECT w FROM WorkEntity w WHERE w.workId IN :ids")
    List<WorkEntity> findByIdIn(List<Long> ids);

    List<WorkEntity> findAllByIdSellerUserAndWorkIsDeletedFalse(Long idSellerUser);
=======
=======

@Repository
public interface ForQueryingWork extends JpaRepository<WorkEntity, Long> {
>>>>>>> Stashed changes
=======

@Repository
public interface ForQueryingWork extends JpaRepository<WorkEntity, Long> {
>>>>>>> Stashed changes
    List<WorkEntity> findByIdSellerUser(Long sellerId);

    @Query("SELECT w, AVG(r.ratingScore) FROM works w LEFT JOIN RatingEntity r ON w.workId = r.workId GROUP BY w.workId")
    List<Object[]> findAllWithAverageRating();

    @Query("SELECT w FROM works w LEFT JOIN FETCH w.workCategory")
    List<WorkEntity> findAllWithCategory();
<<<<<<< Updated upstream
<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
}