package com.example.ApiArgon.Divisiones.Dto;

import com.example.ApiArgon.Divisiones.Model.Divisiones;
import com.example.ApiArgon.Equipos.Dto.EquiposDto;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public record DivisionesDto(UUID idDivision,
        String nombre,
                            String nombreLiga,
                            List<EquiposDto> equiposDtos) {
    public static DivisionesDto of(Divisiones d){
        return new DivisionesDto(
                d.getId(),
                d.getNombre(),
                d.getLigas().getNombre(),
                d.getEquipos().stream().map(EquiposDto::of).collect(Collectors.toList())
        );
    }
}
