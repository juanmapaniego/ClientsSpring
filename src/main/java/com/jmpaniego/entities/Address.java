package com.jmpaniego.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "address")
@Data
public class Address implements Serializable {
    private static final long serialVersionUID = 4974403034604434817L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long address_id;
    private String calle;
    private Integer numero;
}
