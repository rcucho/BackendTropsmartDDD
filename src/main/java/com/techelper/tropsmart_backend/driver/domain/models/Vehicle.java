package com.techelper.tropsmart_backend.driver.domain.models;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "vehicles")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
public class Vehicle {

    @Getter(AccessLevel.PRIVATE)
    @Setter(AccessLevel.PRIVATE)
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id")
    private Driver driver;

    @Column(name = "ownership_card")
    private String ownershipCard;

    @Column(name = "brand")
    private String brand;

    @Column(name = "model")
    private String model;

    @Column(name = "loading_capacity")
    private Double loadingCapacity;

    @Column(name = "fabrication_year")
    @Temporal(TemporalType.TIME)
    private Date fabricationYear;

    @OneToOne
    @JoinColumn(name = "soat_id")
    private Soat soat;
}