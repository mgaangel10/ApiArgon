package com.example.ApiArgon.Ligas.Dto;

import com.example.ApiArgon.Divisiones.Dto.DivisionesDto;
import com.example.ApiArgon.Ligas.Model.Ligas;

import java.util.List;
import java.util.stream.Collectors;

public record LigasDto(String nombre,
                       String pais,
                       List<DivisionesDto> divisionesDtos) {
    public static LigasDto of(Ligas l){
        return new LigasDto(
                l.getNombre(),
                l.getPais(),
                l.getDivisiones().stream().map(DivisionesDto::of).collect(Collectors.toList())
        );
    }
}
