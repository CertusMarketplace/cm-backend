package pe.edu.certus.uimodule.ui.pages.orders.ports.drivers;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CartRequest {
    private List< Long > workIds;
}