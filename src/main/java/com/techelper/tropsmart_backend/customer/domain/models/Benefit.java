package com.techelper.tropsmart_backend.customer.domain.models;

import com.techelper.tropsmart_backend.user.domain.models.User;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "benefits")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
public class Benefit implements Serializable {

    @Getter(AccessLevel.PRIVATE)
    @Setter(AccessLevel.PRIVATE)
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="code", nullable = false)
    private String code;

    @Column(name="value" ,nullable = false)
    private double value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}