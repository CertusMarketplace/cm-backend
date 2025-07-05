package pe.edu.certus.paypalmodule.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import org.hibernate.annotations.Immutable;

import java.math.BigDecimal;

@Entity
@Table(name = "works")
@Immutable
@Getter
public class WorkPriceEntity {

    @Id
    @Column(name = "work_id")
    private Long id;

    @Column(name = "work_price")
    private BigDecimal workPrice;

}