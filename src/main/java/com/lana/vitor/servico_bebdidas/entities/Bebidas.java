package com.lana.vitor.servico_bebdidas.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "db_bebidas")
@Getter
@Setter
public class Bebidas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true, nullable = false)
    private String name;
    private int quantity;
    private boolean status;
    public boolean getStatus(){
        return status;
    }
}
