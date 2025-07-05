package pe.edu.certus.paypalmodule.logic.adapters.driver;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.certus.paypalmodule.logic.model.CreateOrderResponseModel;
import pe.edu.certus.paypalmodule.logic.model.PaymentDetailModel;
import pe.edu.certus.paypalmodule.logic.ports.driver.ForPaypal;

import java.io.IOException;

@RestController
@RequestMapping("/paypal")
public class PaypalAdapter {

    private final ForPaypal forPaypal;

    public PaypalAdapter( ForPaypal forPaypal) {
        this.forPaypal = forPaypal;
    }

    @Secured("Buyer")
    @PostMapping("/create-order")
    public ResponseEntity<?> createOrder(@RequestBody CreateOrderRequest request) {
        try {
            CreateOrderResponseModel response = forPaypal.createOrderFromWork(request.getWorkId());
            return ResponseEntity.ok(response);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating PayPal order: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Secured("Buyer")
    @PostMapping("/capture-order")
    public ResponseEntity<?> captureOrder(@RequestBody CaptureOrderRequest request) {
        try {
            PaymentDetailModel paymentDetails = forPaypal.captureOrder(
                    request.getOrderId(),
                    request.getWorkId(),
                    request.getBuyerUserId()
            );
            return ResponseEntity.ok(paymentDetails);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error capturing PayPal payment: " + e.getMessage());
        }
    }

    @Getter
    static class CreateOrderRequest {
        private Long workId;
    }

    @Getter
    static class CaptureOrderRequest {
        private String orderId;
        private Long workId;
        private Long buyerUserId;
    }
}