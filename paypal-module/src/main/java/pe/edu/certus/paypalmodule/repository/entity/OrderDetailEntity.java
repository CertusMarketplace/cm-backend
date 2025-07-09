package pe.edu.certus.paypalmodule.repository.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "order_details")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_detail_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_order", nullable = false)
    private OrderEntity order;

    @Column(name = "id_work", nullable = false)
    private Long workId;

    @Column(name = "order_detail_quantity", nullable = false)
    private Integer quantity;

    @Column(name = "order_detail_unit_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal unitPrice;

    @Column(name = "order_details_total_price", precision = 19, scale = 2)
    private BigDecimal totalPrice;

    @Column(name = "order_details_status", length = 255)
    private String status;

    @Column(name = "order_details_access_granted")
    private Boolean accessGranted;

    @Column(name = "order_details_created_at")
    private LocalDateTime createdAt;

    @PrePersist
    protected void onPrePersist() {
        createdAt = LocalDateTime.now();
        // Si la cantidad no está seteada, la ponemos en 1 por defecto.
        if (quantity == null) {
            quantity = 1;
        }
        // Calculamos el precio total
        if (unitPrice != null) {
            totalPrice = unitPrice.multiply(new BigDecimal(quantity));
        }
        // Por defecto, al crearse el detalle de la orden, el acceso está concedido.
        if (accessGranted == null) {
            accessGranted = true;
        }
        // Estado inicial
        if (status == null) {
            status = "COMPLETED";
        }
    }
}