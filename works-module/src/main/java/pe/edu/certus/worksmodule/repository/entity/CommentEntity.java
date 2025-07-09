package pe.edu.certus.worksmodule.repository.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet; // CAMBIADO
import java.util.List;
import java.util.Set; // CAMBIADO

@Entity
@Table(name = "comments")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_work", nullable = false)
    private WorkEntity work;

    @Column(name = "id_user", nullable = false)
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_parent_comment")
    private CommentEntity parentComment;

    @Column(name = "comment_body", nullable = false, columnDefinition = "TEXT")
    private String commentBody;

    @Column(name = "comment_is_deleted", nullable = false)
    @Builder.Default
    private Boolean isDeleted = false;

    @Column(name = "comment_created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "comment_updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "parentComment", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CommentEntity> replies = new HashSet<>();

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}