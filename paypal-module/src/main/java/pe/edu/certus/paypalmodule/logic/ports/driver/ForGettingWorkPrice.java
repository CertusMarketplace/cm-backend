package pe.edu.certus.paypalmodule.logic.ports.driver;

import java.math.BigDecimal;
import java.util.Optional;

public interface ForGettingWorkPrice {
    Optional< BigDecimal > findWorkPriceById( Long workId);

}
