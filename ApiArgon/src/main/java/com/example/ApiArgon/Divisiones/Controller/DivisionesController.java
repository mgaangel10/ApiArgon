package com.example.ApiArgon.Divisiones.Controller;

import com.example.ApiArgon.Divisiones.Dto.DivisionesDto;
import com.example.ApiArgon.Divisiones.Dto.MostrarDivisiones;
import com.example.ApiArgon.Divisiones.Model.Divisiones;
import com.example.ApiArgon.Divisiones.Repo.DivisionesRepo;
import com.example.ApiArgon.Equipos.Dto.EquiposDto;
import com.example.ApiArgon.Equipos.Model.Equipos;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class DivisionesController {

    private final DivisionesRepo divisionesRepo;

    @GetMapping("divisiones/{id}")
    public ResponseEntity<List<EquiposDto>> divisionesId(@PathVariable UUID id){

        Optional<Divisiones> divisiones = divisionesRepo.findById(id);
        if (divisiones.isPresent()){
            List<Equipos> equipos = divisiones.get().getEquipos();
            List<EquiposDto> equiposDtos = equipos.stream().map(EquiposDto::of).collect(Collectors.toList());
            return ResponseEntity.ok(equiposDtos);
        }
        return null;
    }

    @GetMapping("mostrar/divisiones")
    public ResponseEntity<List<MostrarDivisiones>> mostrarDivisiones(){
        List<Divisiones> divisiones = divisionesRepo.findAll();
        List<MostrarDivisiones> mostrarDivisiones = divisiones.stream().map(MostrarDivisiones::of).collect(Collectors.toList());
        return ResponseEntity.ok(mostrarDivisiones);
    }
}
