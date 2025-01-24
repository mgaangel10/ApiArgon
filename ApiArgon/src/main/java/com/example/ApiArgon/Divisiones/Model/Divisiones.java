package com.example.ApiArgon.Divisiones.Model;

import com.example.ApiArgon.Equipos.Model.Equipos;
import com.example.ApiArgon.Ligas.Model.Ligas;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Divisiones {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String nombre;

    @ManyToOne
    @JoinColumn(name = "liga_id", nullable = false)
    private Ligas ligas;

    @OneToMany(mappedBy = "divisiones", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Equipos> equipos ;
}
