package com.example.examendisegundotrimestre.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
@Table(name="informacioncliente")
public class InformacionCliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;
    @Column(name = "sexo")
    private String sexo;
    @Column(name = "peso")
    private Double peso;
    @Column(name = "edad")
    private Integer edad;
    @Column(name = "talla")
    private Double talla;
    @Column(name = "tipoActividad")
    private String tipoActividad;
    @Column(name = "observaciones")
    private String observaciones;
}
