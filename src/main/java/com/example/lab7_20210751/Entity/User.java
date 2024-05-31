package com.example.lab7_20210751.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid", nullable = false)
    private Integer id;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "type", length = 100)
    private String type;

    @ManyToOne
    @JoinColumn(name = "authorizedresource")
    private Resource authorizedResource;

    @Column(name = "active")
    private Boolean active;


}
