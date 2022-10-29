package com.techelper.tropsmart_backend.user.domain.models;

import com.techelper.tropsmart_backend.customer.domain.models.Customer;
import com.techelper.tropsmart_backend.driver.domain.models.Driver;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "people")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
public class Person implements Serializable {

    @Getter(AccessLevel.PRIVATE)
    @Setter(AccessLevel.PRIVATE)
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
    private User user;

    @Column(name = "first_name",nullable = false, length = 50)
    private String firstName;

    @Column(name = "last_name",nullable = false, length = 50)
    private String lastName;

    @Column(name = "phone",nullable = false)
    private String phone;

    @Column(name = "person_type", nullable = false)
    private int personType;

    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
    private Customer customer;

    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
    private Driver driver;
}