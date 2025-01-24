package com.example.ApiArgon.Equipos.Controller;

import com.example.ApiArgon.Equipos.Dto.BuscarEquipoDto;
import com.example.ApiArgon.Equipos.Dto.EquiposDto;
import com.example.ApiArgon.Equipos.Dto.FiltradoEquiposDto;
import com.example.ApiArgon.Equipos.Dto.OrdenacionDto;
import com.example.ApiArgon.Equipos.Model.Equipos;
import com.example.ApiArgon.Equipos.Service.EquiposService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class EquiposController {

    private final EquiposService equiposService;

    @PostMapping("equipos/filtrar/{id}")
    public ResponseEntity<List<EquiposDto>> filtrado(@PathVariable UUID id, @RequestBody FiltradoEquiposDto filtradoEquiposDto){
        List<EquiposDto> equiposDtos = equiposService.posiblesFiltrado(id,filtradoEquiposDto);
        return ResponseEntity.status(201).body(equiposDtos);
    }

    @GetMapping("equipos/ordenar/nombre/{id}")
    public ResponseEntity<List<EquiposDto>> ordenarNombre(@PathVariable UUID id){
        List<EquiposDto> equiposDtos = equiposService.ordenarPorNombre(id);
        return ResponseEntity.ok(equiposDtos);
    }

    @GetMapping("equipos/ordenar/fecha/{id}")
    public ResponseEntity<List<EquiposDto>> ordenarFecha(@PathVariable UUID id){
        List<EquiposDto> equiposDtos = equiposService.ordenarPorFecha(id);
        return ResponseEntity.ok(equiposDtos);
    }
    @GetMapping("equipos/ordenar/goles/{id}")
    public ResponseEntity<List<EquiposDto>> ordenarGoles(@PathVariable UUID id){
        List<EquiposDto> equiposDtos = equiposService.ordenarPorGoles(id);
        return ResponseEntity.ok(equiposDtos);
    }
    @GetMapping("equipos/ordenar/titulos/{id}")
    public ResponseEntity<List<EquiposDto>> ordenarTitulos(@PathVariable UUID id){
        List<EquiposDto> equiposDtos = equiposService.ordenarPorTitulos(id);
        return ResponseEntity.ok(equiposDtos);
    }

    @GetMapping("equipos/buscar/{id}")
    public ResponseEntity<List<EquiposDto>> ordenarTitulos(@PathVariable UUID id, @RequestBody BuscarEquipoDto buscarEquipoDto){
        List<EquiposDto> equiposDtos = equiposService.buscar(id,buscarEquipoDto);
        return ResponseEntity.ok(equiposDtos);
    }
}
