package pe.edu.certus.paypalmodule.logic.adapters.driver;

import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pe.edu.certus.paypalmodule.logic.model.CreateOrderResponseModel;
import pe.edu.certus.paypalmodule.logic.model.PaymentDetailModel;
import pe.edu.certus.paypalmodule.logic.ports.driver.ForPaypal;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/paypal")
public class PaypalAdapter {

    private final ForPaypal forPaypal;

    public PaypalAdapter(ForPaypal forPaypal) {
        this.forPaypal = forPaypal;
    }

    @PostMapping("/create-order-from-cart")
    public ResponseEntity<?> createOrderFromCart(@RequestBody CreateOrderFromCartRequest request) {
        try {
            CreateOrderResponseModel response = forPaypal.createOrderFromCart(request.getWorkIds());
            if (response.getApprovalUrl() == null) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", "No se pudo obtener el enlace de aprobación de PayPal."));
            }
            return ResponseEntity.ok(Map.of("approvalUrl", response.getApprovalUrl()));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", "Error al crear la orden de PayPal: " + e.getMessage()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", e.getMessage()));
        }
    }

    @PostMapping("/capture-order")
    public ResponseEntity<?> captureOrder(@RequestBody CaptureOrderRequest request, Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Usuario no autenticado."));
        }
        try {
            Long buyerUserId = Long.parseLong(authentication.getName());
            PaymentDetailModel paymentDetails = forPaypal.captureOrder(
                    request.getOrderId(),
                    request.getWorkIds(),
                    buyerUserId
            );
            return ResponseEntity.ok(paymentDetails);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", "Error al capturar el pago de PayPal: " + e.getMessage()));
        } catch (NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "ID de usuario inválido en el token."));
        }
    }

    @Getter
    @Setter
    static class CreateOrderFromCartRequest {
        private List<Long> workIds;
    }

    @Getter
    @Setter
    static class CaptureOrderRequest {
        private String orderId;
        private List<Long> workIds;
    }
}