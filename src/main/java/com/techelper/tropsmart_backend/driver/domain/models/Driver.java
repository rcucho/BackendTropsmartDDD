package com.techelper.tropsmart_backend.driver.domain.models;

import com.techelper.tropsmart_backend.cargo.domain.models.ServiceRequest;
import com.techelper.tropsmart_backend.user.domain.models.Person;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "drivers")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
public class Driver implements Serializable {

    @Getter(AccessLevel.PRIVATE)
    @Setter(AccessLevel.PRIVATE)
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "licence")
    private String license;

    @OneToMany(mappedBy = "driver")
    private List<Vehicle> vehiclesList = new ArrayList<>();

    @OneToOne(mappedBy = "driver")
    private ServiceRequest serviceRequest;

    @OneToOne(mappedBy = "driver")
    private Qualification qualification;

    @OneToOne
    @JoinColumn(name = "person_id")
    private Person person;
}