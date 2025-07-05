package pe.edu.certus.paypalmodule.logic.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderResponseModel {
    private String orderId;
    private String status;
}