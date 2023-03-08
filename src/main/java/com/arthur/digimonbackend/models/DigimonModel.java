package com.arthur.digimonbackend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "tb_digimon")
public class DigimonModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String img;
    @Column(nullable = false)
    private String level;
}
