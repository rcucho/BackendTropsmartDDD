package com.techelper.tropsmart_backend.user.domain.models;

import com.techelper.tropsmart_backend.customer.domain.models.Benefit;
import com.techelper.tropsmart_backend.userconfiguration.domain.models.Configuration;
import com.techelper.tropsmart_backend.userconfiguration.domain.models.Report;
import lombok.*;
import org.hibernate.annotations.NaturalId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
//import javax.validation.constraints;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="users")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
public class User implements Serializable {

    @Getter(AccessLevel.PRIVATE)
    @Setter(AccessLevel.PRIVATE)
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NaturalId
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @Size(min=6, max = 100)
    @Column(name = "password",nullable = false)
    private String password;

    @CreatedDate
    @Column(name = "created_at",nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @OneToMany(mappedBy = "user")
    private List<Favorite> favouriteCollection = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Block> blockedCollection = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Subscription> subscriptionList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Report> reportList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Benefit> claimedBenefits = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @OneToOne
    @JoinColumn(name = "configuration_id")
    private Configuration configuration;

    @OneToOne
    @JoinColumn(name = "balance_id")
    private Balance balance;



}
