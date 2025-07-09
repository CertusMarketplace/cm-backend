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
import java.util.List;
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
    public CreateOrderResponseModel createOrderFromCart(List<Long> workIds) throws IOException, IllegalArgumentException {
        if (workIds == null || workIds.isEmpty()) {
            throw new IllegalArgumentException("El carrito está vacío.");
        }
        BigDecimal totalAmount = workIds.stream()
                .map(forGettingWorkPrice::findWorkPriceById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        if (totalAmount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto total del carrito es inválido.");
        }
        return createOrder(totalAmount);
    }

    private CreateOrderResponseModel createOrder(BigDecimal amount) throws IOException {
        HttpResponse<Order> response = forManagingPaypal.initiateOrder(amount, CURRENCY_CODE);
        Order order = response.result();
        String approvalUrl = order.links().stream()
                .filter(link -> "approve".equals(link.rel()))
                .findFirst()
                .map(link -> link.href())
                .orElse(null);
        return CreateOrderResponseModel.builder()
                .orderId(order.id())
                .status(order.status())
                .approvalUrl(approvalUrl)
                .build();
    }

    @Override
    public PaymentDetailModel captureOrder(String orderId, List<Long> workIds, Long buyerUserId) throws IOException {
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

            return forPersistingPaypalPayment.createOrderAndSavePayment(paymentDetail, workIds, buyerUserId);
        } else {
            throw new IOException("Fallo al capturar el pago. Estado: " + order.status());
        }
    }
}