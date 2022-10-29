package com.techelper.tropsmart_backend.cargo.domain.models;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "routes")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
public class Route implements Serializable {
    @Getter(AccessLevel.PRIVATE)
    @Setter(AccessLevel.PRIVATE)
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(mappedBy = "route")
    private Service service;

    @OneToMany(mappedBy = "route")
    private List<Location> stopList = new ArrayList<>();

    @Column(name = "distance")
    private Double distance;

    @Column(name = "cargo_units")
    private Integer cargoUnits;
}