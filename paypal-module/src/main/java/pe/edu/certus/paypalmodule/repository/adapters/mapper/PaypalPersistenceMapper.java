package pe.edu.certus.paypalmodule.repository.adapters.mapper;

import org.springframework.stereotype.Component;
import pe.edu.certus.paypalmodule.logic.model.PaymentDetailModel;
import pe.edu.certus.paypalmodule.repository.entity.PaypalPaymentEntity;
import pe.edu.certus.paypalmodule.repository.ports.mapper.ForBridgingPaypalPayment;

@Component
public class PaypalPersistenceMapper implements ForBridgingPaypalPayment {

    @Override
    public PaypalPaymentEntity toPersistence(PaymentDetailModel paymentDetailModel, Long internalOrderId, String transactionType) {
        return PaypalPaymentEntity.builder()
                .idInternalOrder(internalOrderId)
                .paypalPaymentPayerEmail(paymentDetailModel.getPayerEmail())
                .paypalPaymentStatus(paymentDetailModel.getStatus())
                .paypalPaymentTransactionId(paymentDetailModel.getTransactionId())
                .paypalPaymentAmount(paymentDetailModel.getAmount())
                .paypalPaymentCurrency(paymentDetailModel.getCurrency())
                .paypalPaymentTransactionType(transactionType)
                .build();
    }
}