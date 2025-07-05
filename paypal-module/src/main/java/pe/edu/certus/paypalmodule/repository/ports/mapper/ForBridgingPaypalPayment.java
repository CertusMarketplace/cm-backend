package pe.edu.certus.paypalmodule.repository.ports.mapper;

import pe.edu.certus.paypalmodule.logic.model.PaymentDetailModel;
import pe.edu.certus.paypalmodule.repository.entity.PaypalPaymentEntity;

public interface ForBridgingPaypalPayment {
    PaypalPaymentEntity toPersistence(PaymentDetailModel paymentDetailModel, Long internalOrderId, String transactionType);
}