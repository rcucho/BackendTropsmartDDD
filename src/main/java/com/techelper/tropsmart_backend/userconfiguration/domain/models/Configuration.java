package com.techelper.tropsmart_backend.userconfiguration.domain.models;

import com.techelper.tropsmart_backend.user.domain.models.User;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Configurations")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
public class Configuration implements Serializable {

    @Getter(AccessLevel.PRIVATE)
    @Setter(AccessLevel.PRIVATE)
    private static final long  serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(mappedBy = "configuration", cascade = CascadeType.ALL)
    private User user;

    @Column(name="language")
    private String language;

    @Column(name="payment_currency")
    private String paymentCurrency;

    @OneToMany(mappedBy = "configuration")
    private List<PaymentMethod> paymentMethod = new ArrayList<>();
}
