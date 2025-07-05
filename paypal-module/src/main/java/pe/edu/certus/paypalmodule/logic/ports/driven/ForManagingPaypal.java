package pe.edu.certus.paypalmodule.logic.ports.driven;

import com.paypal.http.HttpResponse;
import com.paypal.orders.Order;

import java.io.IOException;
import java.math.BigDecimal;

public interface ForManagingPaypal {
    HttpResponse<Order> initiateOrder(BigDecimal amount, String currency) throws IOException;
    HttpResponse<Order> confirmOrder(String orderId) throws IOException;
}