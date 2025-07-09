package pe.edu.certus.worksmodule.repository.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Where;
import pe.edu.certus.peoplemodule.repository.entity.PeopleEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "works")
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_seller_user", referencedColumnName = "id_user", insertable = false, updatable = false)
    private PeopleEntity seller;

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

    @Builder.Default
    @Column(name = "work_is_deleted")
    private Boolean workIsDeleted = Boolean.FALSE;

    @Column(name = "work_image_url")
    private String workImageUrl;

    @OneToMany(mappedBy = "work", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<WorkImageEntity> images = new HashSet<>();

    @Column(name = "work_file_path")
    private String workFilePath;

    @Column(name = "work_published_at")
    private LocalDateTime workPublishedAt;

    @Column(name = "work_updated_at")
    private LocalDateTime workUpdatedAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "work_status")
    private WorkStatus workStatus;

    @OneToMany(mappedBy = "work", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<RatingEntity> ratings = new HashSet<>(); // CAMBIADO de List a Set

    @OneToMany(mappedBy = "work", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Where(clause = "id_parent_comment IS NULL")
    private Set<CommentEntity> comments = new HashSet<>(); // CAMBIADO de List a Set

    public enum WorkStatus {
        PUBLICADO,
        EN_REVISION,
        RECHAZADO
    }
}