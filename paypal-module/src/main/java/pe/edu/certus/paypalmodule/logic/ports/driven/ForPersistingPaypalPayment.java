package pe.edu.certus.paypalmodule.logic.ports.driven;

import pe.edu.certus.paypalmodule.logic.model.PaymentDetailModel;

import java.util.List;

public interface ForPersistingPaypalPayment {
    PaymentDetailModel createOrderAndSavePayment( PaymentDetailModel paymentDetail, List< Long > workId, Long buyerUserId);
}