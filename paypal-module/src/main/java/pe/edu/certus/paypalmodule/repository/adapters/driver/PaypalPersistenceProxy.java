package pe.edu.certus.paypalmodule.repository.adapters.driver;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import pe.edu.certus.paypalmodule.logic.model.PaymentDetailModel;
import pe.edu.certus.paypalmodule.logic.ports.driven.ForPersistingPaypalPayment;
import pe.edu.certus.paypalmodule.repository.entity.OrderEntity;
import pe.edu.certus.paypalmodule.repository.entity.PaypalPaymentEntity;
import pe.edu.certus.paypalmodule.repository.ports.driver.ForQueryingOrders;
import pe.edu.certus.paypalmodule.repository.ports.driver.ForQueryingPaypalPayments;
import pe.edu.certus.paypalmodule.repository.ports.mapper.ForBridgingPaypalPayment;

@Service
public class PaypalPersistenceProxy implements ForPersistingPaypalPayment {

    private final ForQueryingOrders forQueryingOrders;
    private final ForQueryingPaypalPayments forQueryingPaypalPayments;
    private final ForBridgingPaypalPayment forBridgingPaypalPayment;

    public PaypalPersistenceProxy(ForQueryingOrders forQueryingOrders,
                                  ForQueryingPaypalPayments forQueryingPaypalPayments,
                                  ForBridgingPaypalPayment forBridgingPaypalPayment) {
        this.forQueryingOrders = forQueryingOrders;
        this.forQueryingPaypalPayments = forQueryingPaypalPayments;
        this.forBridgingPaypalPayment = forBridgingPaypalPayment;
    }

    @Override
    @Transactional
    public PaymentDetailModel createOrderAndSavePayment(PaymentDetailModel paymentDetail, Long workId, Long buyerUserId) {
        OrderEntity newOrder = OrderEntity.builder()
                .idUser(buyerUserId)
                .build();
        OrderEntity savedOrder = forQueryingOrders.save(newOrder);
        Long internalOrderId = savedOrder.getOrderId();

        System.out.println("Orden interna creada con ID: " + internalOrderId);

        PaypalPaymentEntity paymentEntity = forBridgingPaypalPayment.toPersistence(paymentDetail, internalOrderId, "Sale");
        forQueryingPaypalPayments.save(paymentEntity);

        System.out.println("Pago de PayPal con transaction ID " + paymentEntity.getPaypalPaymentTransactionId() + " guardado para la orden " + internalOrderId);

        return paymentDetail;
    }
}