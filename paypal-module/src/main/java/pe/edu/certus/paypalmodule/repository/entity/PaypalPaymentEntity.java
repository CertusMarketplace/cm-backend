package pe.edu.certus.paypalmodule.repository.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "paypal_payments")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaypalPaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "paypal_payment_id")
    private Long paypalPaymentId;

    @Column(name = "id_internal_order", nullable = false)
    private Long idInternalOrder;

    @Column(name = "paypal_payment_payer_email", length = 255)
    private String paypalPaymentPayerEmail;

    @Column(name = "paypal_payment_status", length = 50, nullable = false)
    private String paypalPaymentStatus;

    @Column(name = "paypal_payment_transaction_id", length = 255, unique = true)
    private String paypalPaymentTransactionId;

    @Column(name = "paypal_payment_amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal paypalPaymentAmount;

    @Column(name = "paypal_payment_currency", length = 3, nullable = false)
    private String paypalPaymentCurrency;

    @Column(name = "paypal_payment_transaction_type", length = 50)
    private String paypalPaymentTransactionType;

    @Column(name = "paypal_payment_created_at", updatable = false)
    private LocalDateTime paypalPaymentCreatedAt;

    @Column(name = "paypal_payment_updated_at")
    private LocalDateTime paypalPaymentUpdatedAt;

    @PrePersist
    protected void onCreate() {
        paypalPaymentCreatedAt = LocalDateTime.now();
        paypalPaymentUpdatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        paypalPaymentUpdatedAt = LocalDateTime.now();
    }
}