package pe.edu.certus.paypalmodule.repository.adapters.driver;

import com.paypal.core.PayPalHttpClient;
import com.paypal.http.HttpResponse;
import com.paypal.orders.*;
import org.springframework.stereotype.Service;
import pe.edu.certus.paypalmodule.logic.ports.driven.ForManagingPaypal;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@Service
public class PaypalServiceProxy implements ForManagingPaypal {

    private final PayPalHttpClient client;
    private final String appBaseUrl = "http://localhost:8080";

    public PaypalServiceProxy(PayPalHttpClient client) {
        this.client = client;
    }

    @Override
    public HttpResponse<Order> initiateOrder(BigDecimal amount, String currency) throws IOException {
        OrdersCreateRequest request = new OrdersCreateRequest();
        request.prefer("return=representation");
        request.requestBody(buildRequestBody(amount, currency));

        HttpResponse<Order> response = client.execute(request);

        if (response.statusCode() >= 200 && response.statusCode() < 300) {
            System.out.println("PayPal Order created with ID: " + response.result().id());
        } else {
            System.err.println("Failed to create PayPal order. Status: " + response.statusCode());
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
                .returnUrl(appBaseUrl + "/marketplace/works?payment_status=success")
                .cancelUrl(appBaseUrl + "/marketplace/works?payment_status=cancelled");

        orderRequest.applicationContext(applicationContext);

        return orderRequest;
    }
}