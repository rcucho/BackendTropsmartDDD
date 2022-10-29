package com.techelper.tropsmart_backend.cargo.domain.models;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "locations")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
public class Location implements Serializable {

    @Getter(AccessLevel.PRIVATE)
    @Setter(AccessLevel.PRIVATE)
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "departure_latitude")
    private Double departureLatitude;

    @Column(name = "departure_longitude")
    private Double departureLongitude;

    @Column(name = "departure_altitude")
    private Double departureAltitude;

    @Column(name = "arrival_latitude")
    private Double arrivalLatitude;

    @Column(name = "arrival_longitude")
    private Double arrivalLongitude;

    @Column(name = "arrival_altitude")
    private Double arrivalAltitude;

    @OneToOne
    @JoinColumn(name = "cargo_id")
    Cargo cargo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "route_id")
    Route route;

}