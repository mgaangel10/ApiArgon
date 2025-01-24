package com.example.ApiArgon.Ligas.Model;

import com.example.ApiArgon.Divisiones.Model.Divisiones;
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
public class Ligas {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String nombre;

    private String pais;

    @OneToMany(mappedBy = "ligas", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Divisiones> divisiones ;
}
