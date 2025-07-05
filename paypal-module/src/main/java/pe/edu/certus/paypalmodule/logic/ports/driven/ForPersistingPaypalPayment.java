package pe.edu.certus.paypalmodule.logic.ports.driven;

import pe.edu.certus.paypalmodule.logic.model.PaymentDetailModel;

public interface ForPersistingPaypalPayment {
    PaymentDetailModel createOrderAndSavePayment(PaymentDetailModel paymentDetail, Long workId, Long buyerUserId);
}