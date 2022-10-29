package com.techelper.tropsmart_backend.cargo.domain.models;

import com.techelper.tropsmart_backend.customer.domain.models.Customer;
import com.techelper.tropsmart_backend.driver.domain.models.Review;
import com.techelper.tropsmart_backend.user.domain.models.Price;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "cargoes")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
public class Cargo implements Serializable {

    @Getter(AccessLevel.PRIVATE)
    @Setter(AccessLevel.PRIVATE)
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "serviceId")
    private Service service;

    @Column(name = "weight")
    private double weight;

    @Column(name = "description", nullable = false)
    private String description;

    @OneToOne
    @JoinColumn(name = "price")
    private Price price;

    @Column(name = "cargo_status")
    private String cargoStatus;

    @Column(name = "cargo_type")
    private String cargoType;

    @OneToOne(mappedBy = "cargo", cascade = CascadeType.ALL)
    private Review review;

    @OneToOne(mappedBy = "cargo")
    private Location location;



}