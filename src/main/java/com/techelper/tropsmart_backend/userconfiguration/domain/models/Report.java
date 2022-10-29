package com.techelper.tropsmart_backend.userconfiguration.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.techelper.tropsmart_backend.user.domain.models.User;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "reports")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"startedTime"}, allowGetters = true)
@Data
@NoArgsConstructor
public class Report implements Serializable {

    @Getter(AccessLevel.PRIVATE)
    @Setter(AccessLevel.PRIVATE)
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}