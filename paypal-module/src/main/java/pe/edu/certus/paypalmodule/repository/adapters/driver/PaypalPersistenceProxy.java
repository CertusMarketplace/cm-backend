package pe.edu.certus.paypalmodule.repository.adapters.driver;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import pe.edu.certus.paypalmodule.logic.model.PaymentDetailModel;
import pe.edu.certus.paypalmodule.logic.ports.driven.ForPersistingPaypalPayment;
import pe.edu.certus.paypalmodule.logic.ports.driver.ForGettingWorkPrice;
import pe.edu.certus.paypalmodule.repository.entity.OrderDetailEntity;
import pe.edu.certus.paypalmodule.repository.entity.OrderEntity;
import pe.edu.certus.paypalmodule.repository.entity.PaypalPaymentEntity;
import pe.edu.certus.paypalmodule.repository.ports.driver.ForQueryingOrderDetails;
import pe.edu.certus.paypalmodule.repository.ports.driver.ForQueryingOrders;
import pe.edu.certus.paypalmodule.repository.ports.driver.ForQueryingPaypalPayments;
import pe.edu.certus.paypalmodule.repository.ports.mapper.ForBridgingPaypalPayment;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PaypalPersistenceProxy implements ForPersistingPaypalPayment {

    private final ForQueryingOrders forQueryingOrders;
    private final ForQueryingPaypalPayments forQueryingPaypalPayments;
    private final ForBridgingPaypalPayment forBridgingPaypalPayment;
    private final ForQueryingOrderDetails forQueryingOrderDetails;
    private final ForGettingWorkPrice forGettingWorkPrice;

    public PaypalPersistenceProxy(ForQueryingOrders forQueryingOrders,
                                  ForQueryingPaypalPayments forQueryingPaypalPayments,
                                  ForBridgingPaypalPayment forBridgingPaypalPayment,
                                  ForQueryingOrderDetails forQueryingOrderDetails,
                                  ForGettingWorkPrice forGettingWorkPrice) {
        this.forQueryingOrders = forQueryingOrders;
        this.forQueryingPaypalPayments = forQueryingPaypalPayments;
        this.forBridgingPaypalPayment = forBridgingPaypalPayment;
        this.forQueryingOrderDetails = forQueryingOrderDetails;
        this.forGettingWorkPrice = forGettingWorkPrice;
    }

    @Override
    @Transactional
    public PaymentDetailModel createOrderAndSavePayment(PaymentDetailModel paymentDetail, List<Long> workIds, Long buyerUserId) {
        // 1. Crear la orden principal
        OrderEntity newOrder = OrderEntity.builder()
                .idUser(buyerUserId)
                .build();
        OrderEntity savedOrder = forQueryingOrders.save(newOrder);
        Long internalOrderId = savedOrder.getOrderId();

        System.out.println("Orden interna creada con ID: " + internalOrderId);

        // 2. Crear los detalles de la orden para cada trabajo
        for (Long workId : workIds) {
            BigDecimal price = forGettingWorkPrice.findWorkPriceById(workId)
                    .orElseThrow(() -> new IllegalArgumentException("No se pudo encontrar el precio para el trabajo con ID: " + workId));

            // Creamos la entidad de detalle de orden con los datos necesarios
            OrderDetailEntity orderDetail = OrderDetailEntity.builder()
                    .order(savedOrder)
                    .workId(workId)
                    .quantity(1) // Asumimos que siempre se compra 1 unidad de cada trabajo digital
                    .unitPrice(price)
                    .status("PAID") // Estado explícito de pago
                    .accessGranted(true) // Se concede el acceso inmediatamente
                    .build();

            forQueryingOrderDetails.save(orderDetail);
            System.out.println("Detalle de orden guardado para el trabajo ID: " + workId + " en la orden " + internalOrderId);
        }

        // 3. Guardar la información del pago de PayPal
        PaypalPaymentEntity paymentEntity = forBridgingPaypalPayment.toPersistence(paymentDetail, internalOrderId, "Sale");
        forQueryingPaypalPayments.save(paymentEntity);

        System.out.println("Pago de PayPal con transaction ID " + paymentEntity.getPaypalPaymentTransactionId() + " guardado para la orden " + internalOrderId);

        return paymentDetail;
    }
}