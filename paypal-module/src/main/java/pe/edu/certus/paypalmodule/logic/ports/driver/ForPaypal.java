package pe.edu.certus.paypalmodule.logic.ports.driver;

import pe.edu.certus.paypalmodule.logic.model.CreateOrderResponseModel;
import pe.edu.certus.paypalmodule.logic.model.PaymentDetailModel;

import java.io.IOException;

public interface ForPaypal {
    CreateOrderResponseModel createOrderFromWork(Long workId) throws IOException, IllegalArgumentException;
    PaymentDetailModel captureOrder(String orderId, Long workId, Long buyerUserId) throws IOException;
}