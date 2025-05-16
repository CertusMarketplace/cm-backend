package pe.edu.certus.worksmodule.repository.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity( name = "works" )
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkEntity {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "work_id" )
    private Integer workId;

    @Transient
    @Column( name = "id_seller_user" )
    private Integer idSellerUser;

    @Transient
    @Column( name = "id_work_category" )
    private Integer idWorkCategory;

    @Column( name = "work_title" )
    private String workTitle;

    @Column( name = "work_description" )
    private String workDescription;

    @Column( name = "work_category" )
    private String workCategory;

    @Column( name = "work_price" )
    private BigDecimal workPrice;

    @Column( name = "work_is_deleted" )
    private Boolean workIsDeleted;

    @Column( name = "work_image_url" )
    private String workImageUrl;

    @Column( name = "work_published_at" )
    private LocalDateTime workPublishedAt;

    @Column( name = "work_updated_at" )
    private LocalDateTime workUpdatedAt;

    @Column( name = "work_status" )
    private WorkStatus workStatus;

    public enum WorkStatus {
        EN_REVISION, PUBLICADO, RECHAZADO
    }

}
