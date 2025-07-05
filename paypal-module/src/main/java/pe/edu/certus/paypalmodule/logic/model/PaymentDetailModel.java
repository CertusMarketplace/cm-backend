package pe.edu.certus.paypalmodule.logic.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class PaymentDetailModel {
    private String orderId;
    private String transactionId;
    private String status;
    private BigDecimal amount;
    private String currency;
    private String payerEmail;
    private String payerName;
}