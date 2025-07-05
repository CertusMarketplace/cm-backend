package pe.edu.certus.paypalmodule.repository.adapters.driver;

import com.paypal.core.PayPalHttpClient;
import com.paypal.http.HttpResponse;
import com.paypal.orders.*;
import org.springframework.stereotype.Service;
import pe.edu.certus.paypalmodule.logic.ports.driven.ForManagingPaypal;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class PaypalServiceProxy implements ForManagingPaypal {

    private final PayPalHttpClient client;

    public PaypalServiceProxy(PayPalHttpClient client) {
        this.client = client;
    }

    @Override
    public HttpResponse<Order> initiateOrder(BigDecimal amount, String currency) throws IOException {
        OrdersCreateRequest request = new OrdersCreateRequest();
        request.prefer("return=representation");
        request.requestBody(buildRequestBody(amount, currency));

        HttpResponse<Order> response = client.execute(request);

        Order order = response.result();
        Optional<LinkDescription> approveLink = order.links().stream()
                .filter(link -> "approve".equals(link.rel()))
                .findFirst();

        if (approveLink.isPresent()) {
            System.out.println("========================================================================");
            System.out.println("ENLACE DE APROBACIÓN DE PAYPAL (PEGAR EN EL NAVEGADOR):");
            System.out.println(approveLink.get().href());
            System.out.println("========================================================================");
        } else {
            System.out.println("ADVERTENCIA: No se encontró el enlace de aprobación en la respuesta de PayPal.");
        }

        return response;
    }

    @Override
    public HttpResponse<Order> confirmOrder(String orderId) throws IOException {
        OrdersCaptureRequest request = new OrdersCaptureRequest(orderId);
        request.requestBody(new OrderRequest());
        return client.execute(request);
    }

    private OrderRequest buildRequestBody(BigDecimal amount, String currency) {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.checkoutPaymentIntent("CAPTURE");

        AmountWithBreakdown amountWithBreakdown = new AmountWithBreakdown()
                .currencyCode(currency)
                .value(amount.toPlainString());

        PurchaseUnitRequest purchaseUnitRequest = new PurchaseUnitRequest()
                .amountWithBreakdown(amountWithBreakdown);

        orderRequest.purchaseUnits(List.of(purchaseUnitRequest));

        ApplicationContext applicationContext = new ApplicationContext()
                .brandName("CertUs Marketplace")
                .returnUrl("https://example.com/payment/success")
                .cancelUrl("https://example.com/payment/cancel");
        orderRequest.applicationContext(applicationContext);

        return orderRequest;
    }
}