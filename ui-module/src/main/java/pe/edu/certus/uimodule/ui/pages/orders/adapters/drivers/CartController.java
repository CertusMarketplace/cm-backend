package pe.edu.certus.uimodule.ui.pages.orders.adapters.drivers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.edu.certus.uimodule.ui.pages.orders.ports.drivers.CartRequest;
import pe.edu.certus.worksmodule.logic.model.WorkModel;
import pe.edu.certus.worksmodule.logic.ports.driver.ForWork;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/marketplace/cart")
public class CartController {

    private final ForWork<WorkModel, Long> forWork;

    public CartController(ForWork<WorkModel, Long> forWork) {
        this.forWork = forWork;
    }

    @PostMapping("/content")
    public String getCartContent(@RequestBody CartRequest cartRequest, Model model) {
        List<WorkModel> cartItems = Collections.emptyList();
        BigDecimal total = BigDecimal.ZERO;

        List<Long> workIds = cartRequest.getWorkIds();

        if (workIds != null && !workIds.isEmpty()) {
            cartItems = forWork.findWorksByIds(workIds);

            total = cartItems.stream()
                    .map(WorkModel::getWorkPrice)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        }

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("total", total);

        String workIdsJson = workIds.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(",", "[", "]"));

        model.addAttribute("workIdsJson", workIdsJson);

        return "fragments/cart-content";
    }
}