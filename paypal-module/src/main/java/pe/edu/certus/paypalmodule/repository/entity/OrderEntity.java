package pe.edu.certus.paypalmodule.repository.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "id_user", nullable = false)
    private Long idUser;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @PrePersist
    protected void onCreate() {
        orderDate = LocalDateTime.now();
    }
}