package pe.edu.certus.paypalmodule.logic.ports.driver;

import pe.edu.certus.paypalmodule.logic.model.CreateOrderResponseModel;
import pe.edu.certus.paypalmodule.logic.model.PaymentDetailModel;

import java.io.IOException;
import java.util.List; // Importar List

public interface ForPaypal {
    CreateOrderResponseModel createOrderFromCart(List<Long> workIds) throws IOException, IllegalArgumentException;
    PaymentDetailModel captureOrder(String orderId, List<Long> workIds, Long buyerUserId) throws IOException; // MODIFICADO para aceptar lista
}