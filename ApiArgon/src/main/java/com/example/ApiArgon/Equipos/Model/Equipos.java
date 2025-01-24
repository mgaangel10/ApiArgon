package com.example.ApiArgon.Equipos.Model;

import com.example.ApiArgon.Divisiones.Model.Divisiones;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class    Equipos {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String imagen;

    private String nombre;

    private String ciudad;

    private int golesTotales = 0;

    private int titulosObtenidos = 0;

    private String nombreEstadio;

    private LocalDate fundado;

    @ManyToOne
    @JoinColumn(name = "division_id", nullable = false)
    private Divisiones divisiones;
}

