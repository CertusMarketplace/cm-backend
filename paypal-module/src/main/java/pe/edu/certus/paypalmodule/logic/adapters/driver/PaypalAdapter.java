package pe.edu.certus.paypalmodule.logic.adapters.driver;

import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.certus.paypalmodule.logic.model.CreateOrderResponseModel;
import pe.edu.certus.paypalmodule.logic.model.PaymentDetailModel;
import pe.edu.certus.paypalmodule.logic.ports.driver.ForPaypal;

import java.io.IOException;
import java.util.List;

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
            String approvalUrl = response.getApprovalUrl(); //
            if (approvalUrl == null) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se pudo obtener el enlace de PayPal.");
            }
            return ResponseEntity.ok(new ApprovalUrlResponse(approvalUrl));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating PayPal order: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Getter
    @AllArgsConstructor
    static class ApprovalUrlResponse {
        private String approvalUrl;
    }

    @PostMapping("/capture-order")
    public ResponseEntity<?> captureOrder(@RequestBody CaptureOrderRequest request) {
        try {
            PaymentDetailModel paymentDetails = forPaypal.captureOrder(
                    request.getOrderId(),
                    request.getWorkIds(),
                    request.getBuyerUserId()
            );
            return ResponseEntity.ok(paymentDetails);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error capturing PayPal payment: " + e.getMessage());
        }
    }

    @Getter
    static class CreateOrderFromCartRequest {
        private List<Long> workIds;
    }

    @Getter
    static class CaptureOrderRequest {
        private String orderId;
        private List<Long> workIds;
        private Long buyerUserId;
    }
}
