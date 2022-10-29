package com.techelper.tropsmart_backend.customer.domain.models;

import com.techelper.tropsmart_backend.cargo.domain.models.Cargo;
import com.techelper.tropsmart_backend.user.domain.models.Person;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="customers")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
public class Customer implements Serializable {

    @Getter(AccessLevel.PRIVATE)
    @Setter(AccessLevel.PRIVATE)
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "credits")
    private double credits;

    @OneToMany(mappedBy = "customer")
    private List<Cargo> cargoList = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "person_id")
    private Person person;
}