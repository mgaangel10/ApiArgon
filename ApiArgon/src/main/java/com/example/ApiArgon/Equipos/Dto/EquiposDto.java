package com.example.ApiArgon.Equipos.Dto;

import com.example.ApiArgon.Equipos.Model.Equipos;

import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public record EquiposDto(UUID idEquipo,
        String imagen,
                         String nombre,
                         String ciudad,
                         String nombreEstadio,
                         LocalDate fundaoo,
                         int golesTotales,
                         int titulosObtenidos,
                         String division) {
    public static EquiposDto of(Equipos e){
        return new EquiposDto(
                e.getId(),
                e.getImagen(),
                e.getNombre(),
                e.getCiudad(),
                e.getNombreEstadio(),
                e.getFundado(),
                e.getGolesTotales(),
                e.getTitulosObtenidos(),
                e.getDivisiones().getNombre()
        );
    }
}
