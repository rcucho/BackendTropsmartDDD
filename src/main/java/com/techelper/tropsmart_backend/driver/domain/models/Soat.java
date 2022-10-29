package com.techelper.tropsmart_backend.driver.domain.models;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "soat")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
public class Soat implements Serializable {
    @Getter(AccessLevel.PRIVATE)
    @Setter(AccessLevel.PRIVATE)
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="emission_date",nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date EmissionDate;

    @Column(name = "expire_date",nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date ExpireDate;

    @OneToOne(mappedBy="soat")
    private Vehicle vehicle;

    @Column(name = "associate_name")
    private String associateName;

    @Column(name = "service_type")
    private String serviceType;


}