package com.example.ApiArgon.Divisiones.Dto;

import com.example.ApiArgon.Divisiones.Model.Divisiones;

import java.util.UUID;

public record MostrarDivisiones(UUID id,
                                String nombre) {
    public static MostrarDivisiones of(Divisiones d){
        return new MostrarDivisiones(
                d.getId(),
                d.getNombre()
        );
    }
}
