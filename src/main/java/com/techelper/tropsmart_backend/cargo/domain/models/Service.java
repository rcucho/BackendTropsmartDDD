package com.techelper.tropsmart_backend.cargo.domain.models;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "services")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
public class Service implements Serializable {
    @Getter(AccessLevel.PRIVATE)
    @Setter(AccessLevel.PRIVATE)
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_request_id")
    private ServiceRequest servicesRequest;

    @OneToMany(mappedBy = "service")
    private List<Cargo> cargoList = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "route_id")
    public Route route;

    @CreatedDate
    @Column(name="start_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;

    @Column(name="finish_time",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date finishTime;

    //@Enumerated(EnumType.STRING)
    @Column(name = "service_state")
    private String serviceState;
}