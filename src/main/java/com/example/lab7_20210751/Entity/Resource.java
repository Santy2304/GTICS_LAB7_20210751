package com.example.lab7_20210751.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "resources", schema = "niupay")
public class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resourceid", nullable = false)
    private Integer idResource;

    @Column(name = "name", length = 100)
    private String name;

}