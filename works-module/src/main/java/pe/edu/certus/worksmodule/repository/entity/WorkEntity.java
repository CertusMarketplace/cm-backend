package pe.edu.certus.worksmodule.repository.entity;

import jakarta.persistence.*;
import lombok.*;
<<<<<<< Updated upstream
<<<<<<< Updated upstream
<<<<<<< Updated upstream
<<<<<<< Updated upstream
import org.hibernate.annotations.Where;
import pe.edu.certus.peoplemodule.repository.entity.PeopleEntity;

=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

<<<<<<< Updated upstream
<<<<<<< Updated upstream
<<<<<<< Updated upstream
<<<<<<< Updated upstream
@Entity
@Table(name = "works")
=======
@Entity(name = "works")
>>>>>>> Stashed changes
=======
@Entity(name = "works")
>>>>>>> Stashed changes
=======
@Entity(name = "works")
>>>>>>> Stashed changes
=======
@Entity(name = "works")
>>>>>>> Stashed changes
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "work_id")
    private Long workId;

<<<<<<< Updated upstream
<<<<<<< Updated upstream
<<<<<<< Updated upstream
<<<<<<< Updated upstream
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_seller_user", referencedColumnName = "id_user", insertable = false, updatable = false)
    private PeopleEntity seller;

=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
    @Column(name = "id_seller_user")
    private Long idSellerUser;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_work_category")
    private WorkCategoryEntity workCategory;

    @Column(name = "work_title")
    private String workTitle;

    @Column(name = "work_description")
    private String workDescription;

    @Column(name = "work_price")
    private BigDecimal workPrice;

<<<<<<< Updated upstream
<<<<<<< Updated upstream
<<<<<<< Updated upstream
<<<<<<< Updated upstream
    @Builder.Default
    @Column(name = "work_is_deleted")
    private Boolean workIsDeleted = Boolean.FALSE;
=======
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
    @Column(name = "work_is_deleted")
    private Boolean workIsDeleted;
>>>>>>> Stashed changes

    @Column(name = "work_image_url")
    private String workImageUrl;

<<<<<<< Updated upstream
<<<<<<< Updated upstream
<<<<<<< Updated upstream
<<<<<<< Updated upstream
    @OneToMany(mappedBy = "work", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<WorkImageEntity> images = new HashSet<>();

    @Column(name = "work_file_path")
    private String workFilePath;

=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
    @Column(name = "work_published_at")
    private LocalDateTime workPublishedAt;

    @Column(name = "work_updated_at")
    private LocalDateTime workUpdatedAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "work_status")
    private WorkStatus workStatus;

<<<<<<< Updated upstream
<<<<<<< Updated upstream
<<<<<<< Updated upstream
<<<<<<< Updated upstream
    @OneToMany(mappedBy = "work", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<RatingEntity> ratings = new ArrayList<>();

    @OneToMany(mappedBy = "work", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Where(clause = "id_parent_comment IS NULL")
    private List<CommentEntity> comments = new ArrayList<>();

=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
    public enum WorkStatus {
        PUBLICADO,
        EN_REVISION,
        RECHAZADO
    }
}