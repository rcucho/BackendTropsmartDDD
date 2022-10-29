package com.techelper.tropsmart_backend.user.domain.models;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "plans")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
public class Plan implements Serializable {

    @Getter(AccessLevel.PRIVATE)
    @Setter(AccessLevel.PRIVATE)
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", length = 50)
    private String name;

    //In days
    @Column(name = "duration")
    private Integer duration;

    @OneToOne
    @JoinColumn(name = "price_id")
    private Price price;

    @OneToMany(mappedBy = "plan")
    private List<Subscription> subscriptionList = new ArrayList<>();
}