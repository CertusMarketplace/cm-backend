package pe.edu.certus.paypalmodule.repository.adapters.driver;

import org.springframework.stereotype.Service;
import pe.edu.certus.paypalmodule.logic.ports.driver.ForGettingWorkPrice;
import pe.edu.certus.paypalmodule.repository.entity.WorkPriceEntity;
import pe.edu.certus.paypalmodule.repository.ports.driver.ForQueryingWorkInPaypal;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class WorkPriceManager implements ForGettingWorkPrice {

    private final ForQueryingWorkInPaypal forQueryingWorkPrice;

    public WorkPriceManager(ForQueryingWorkInPaypal forQueryingWorkPrice) {
        this.forQueryingWorkPrice = forQueryingWorkPrice;
    }

    @Override
    public Optional<BigDecimal> findWorkPriceById(Long workId) {
        return forQueryingWorkPrice.findById(workId)
                .map(WorkPriceEntity::getWorkPrice);
    }
}