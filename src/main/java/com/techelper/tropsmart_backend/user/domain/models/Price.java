package com.techelper.tropsmart_backend.user.domain.models;

import com.techelper.tropsmart_backend.cargo.domain.models.Cargo;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "prices")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
public class Price implements Serializable {
    @Getter(AccessLevel.PRIVATE)
    @Setter(AccessLevel.PRIVATE)
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="total_price",nullable = false, updatable = false)
    private Double totalPrice;

    @Column(name="tax",nullable = false, updatable = false)
    private Double tax;

    @OneToOne(mappedBy = "price", cascade = CascadeType.ALL)
    private Plan plan;

    @OneToOne(mappedBy = "price", cascade = CascadeType.ALL)
    private Cargo cargo;

    @Column(name = "price_type")
    private int priceType;

}