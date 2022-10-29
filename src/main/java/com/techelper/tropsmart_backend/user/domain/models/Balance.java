package com.techelper.tropsmart_backend.user.domain.models;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "balances")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
public class Balance implements Serializable {

    @Getter(AccessLevel.PRIVATE)
    @Setter(AccessLevel.PRIVATE)
    private static final long  serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="added_money", nullable = false)
    private double addedMoney;

    @Column(name="spent_money", nullable = false)
    private double spentMoney;

    @OneToOne(mappedBy = "balance", cascade = CascadeType.ALL)
    private User user;


    public void rechargeMoney(double money)
    {
        this.addedMoney += money;
    }

    public void spendMoney(double money)
    {
        this.spentMoney += money;
    }

}