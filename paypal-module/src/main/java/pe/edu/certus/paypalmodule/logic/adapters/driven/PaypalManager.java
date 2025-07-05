// Archivo MODIFICADO: paypal-module/src/main/java/pe/edu/certus/paypalmodule/logic/adapters/driven/PaypalManager.java
package pe.edu.certus.paypalmodule.logic.adapters.driven;

import com.paypal.http.HttpResponse;
import com.paypal.orders.Order;
import com.paypal.orders.PurchaseUnit;
import org.springframework.stereotype.Service;
import pe.edu.certus.paypalmodule.logic.model.CreateOrderResponseModel;
import pe.edu.certus.paypalmodule.logic.model.PaymentDetailModel;
import pe.edu.certus.paypalmodule.logic.ports.driven.ForManagingPaypal;
import pe.edu.certus.paypalmodule.logic.ports.driven.ForPersistingPaypalPayment;
import pe.edu.certus.paypalmodule.logic.ports.driver.ForGettingWorkPrice;
import pe.edu.certus.paypalmodule.logic.ports.driver.ForPaypal;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Optional;

@Service
public class PaypalManager implements ForPaypal {

    private final ForManagingPaypal forManagingPaypal;
    private final ForPersistingPaypalPayment forPersistingPaypalPayment;
    private final ForGettingWorkPrice forGettingWorkPrice;

    private static final String CURRENCY_CODE = "USD";

    public PaypalManager(ForManagingPaypal forManagingPaypal,
                         ForPersistingPaypalPayment forPersistingPaypalPayment,
                         ForGettingWorkPrice forGettingWorkPrice) {
        this.forManagingPaypal = forManagingPaypal;
        this.forPersistingPaypalPayment = forPersistingPaypalPayment;
        this.forGettingWorkPrice = forGettingWorkPrice;
    }

    @Override
    public CreateOrderResponseModel createOrderFromWork(Long workId) throws IOException, IllegalArgumentException {
        // 4. Usa el puerto para obtener el precio
        Optional<BigDecimal> amountOptional = forGettingWorkPrice.findWorkPriceById(workId);

        if (amountOptional.isEmpty()) {
            throw new IllegalArgumentException("Work not found with ID: " + workId);
        }

        BigDecimal amount = amountOptional.get();
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Invalid price for work ID: " + workId);
        }

        HttpResponse<Order> response = forManagingPaypal.initiateOrder(amount, CURRENCY_CODE);
        Order order = response.result();

        return CreateOrderResponseModel.builder()
                .orderId(order.id())
                .status(order.status())
                .build();
    }

    @Override
    public PaymentDetailModel captureOrder(String orderId, Long workId, Long buyerUserId) throws IOException {
        // ... (El resto de este método no cambia, ya que no depende del precio del trabajo)
        HttpResponse<Order> response = forManagingPaypal.confirmOrder(orderId);
        Order order = response.result();

        if (order.status() != null && "COMPLETED".equals(order.status())) {
            PurchaseUnit purchaseUnit = order.purchaseUnits().get(0);
            String transactionId = purchaseUnit.payments().captures().get(0).id();
            BigDecimal capturedAmount = new BigDecimal(purchaseUnit.payments().captures().get(0).amount().value());
            String currency = purchaseUnit.payments().captures().get(0).amount().currencyCode();

            PaymentDetailModel paymentDetail = PaymentDetailModel.builder()
                    .orderId(order.id())
                    .transactionId(transactionId)
                    .status(order.status())
                    .amount(capturedAmount)
                    .currency(currency)
                    .payerEmail(order.payer().email())
                    .payerName(order.payer().name().givenName() + " " + order.payer().name().surname())
                    .build();

            return forPersistingPaypalPayment.createOrderAndSavePayment(paymentDetail, workId, buyerUserId);
        } else {
            throw new IOException("Failed to capture payment. Status: " + order.status());
        }
    }
}