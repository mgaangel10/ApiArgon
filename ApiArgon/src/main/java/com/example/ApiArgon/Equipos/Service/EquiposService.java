package com.example.ApiArgon.Equipos.Service;

import com.example.ApiArgon.Divisiones.Model.Divisiones;
import com.example.ApiArgon.Divisiones.Repo.DivisionesRepo;
import com.example.ApiArgon.Equipos.Dto.BuscarEquipoDto;
import com.example.ApiArgon.Equipos.Dto.EquiposDto;
import com.example.ApiArgon.Equipos.Dto.FiltradoEquiposDto;
import com.example.ApiArgon.Equipos.Dto.OrdenacionDto;
import com.example.ApiArgon.Equipos.Model.Equipos;
import com.example.ApiArgon.Equipos.Repo.EquiposRepo;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EquiposService {

    private final EquiposRepo equiposRepo;
    private final DivisionesRepo divisionesRepo;


    public List<EquiposDto> posiblesFiltrado(UUID idDivision,FiltradoEquiposDto filtradoEquiposDto){
        Optional<Divisiones> divisiones = divisionesRepo.findById(idDivision);


        if (divisiones.get().getNombre().equalsIgnoreCase("Primera Division")){
            if (filtradoEquiposDto.numeroGoles()==0&&filtradoEquiposDto.numeroTitulos()!=0){
                List<Equipos> equiposDtos = equiposRepo.findByTitulosObtenidos(filtradoEquiposDto.numeroTitulos());
                List<Equipos> equipos = equiposDtos.stream().filter(equipos1 -> equipos1.getDivisiones().getNombre().equalsIgnoreCase("Primera Division")).collect(Collectors.toList());
                List<EquiposDto> equiposDtos1 = equipos.stream().map(EquiposDto::of).collect(Collectors.toList());

                return equiposDtos1;
            }
            if (filtradoEquiposDto.numeroGoles()!=0&&filtradoEquiposDto.numeroTitulos()==0){
                List<Equipos> equiposDtos = equiposRepo.findByGolesTotales(filtradoEquiposDto.numeroGoles());
                List<Equipos> equipos = equiposDtos.stream().filter(equipos1 -> equipos1.getDivisiones().getNombre().equalsIgnoreCase("Primera Division")).collect(Collectors.toList());

                List<EquiposDto> equiposDtos1 = equipos.stream().map(EquiposDto::of).collect(Collectors.toList());
                return equiposDtos1;
            }

            if (filtradoEquiposDto.numeroGoles()!=0&&filtradoEquiposDto.numeroTitulos()!=0){
                List<Equipos> equiposDtos = equiposRepo.findByGolesTotalesAndTitulosObtenidos(filtradoEquiposDto.numeroGoles(), filtradoEquiposDto.numeroTitulos());
                List<Equipos> equipos = equiposDtos.stream().filter(equipos1 -> equipos1.getDivisiones().getNombre().equalsIgnoreCase("Primera Division")).collect(Collectors.toList());
                List<EquiposDto> equiposDtos1 = equipos.stream().map(EquiposDto::of).collect(Collectors.toList());
                return equiposDtos1;
            }

        } else {

            if (filtradoEquiposDto.numeroGoles()==0&&filtradoEquiposDto.numeroTitulos()!=0){
                List<Equipos> equiposDtos = equiposRepo.findByTitulosObtenidos(filtradoEquiposDto.numeroTitulos());
                List<Equipos> equipos = equiposDtos.stream().filter(equipos1 -> equipos1.getDivisiones().getNombre().equalsIgnoreCase("Segunda Division")).collect(Collectors.toList());
                List<EquiposDto> equiposDtos1 = equipos.stream().map(EquiposDto::of).collect(Collectors.toList());
                return equiposDtos1;
            }
            if (filtradoEquiposDto.numeroGoles()!=0&&filtradoEquiposDto.numeroTitulos()==0){
                List<Equipos> equiposDtos = equiposRepo.findByGolesTotales(filtradoEquiposDto.numeroGoles());
                List<Equipos> equipos = equiposDtos.stream().filter(equipos1 -> equipos1.getDivisiones().getNombre().equalsIgnoreCase("Segunda Division")).collect(Collectors.toList());
                List<EquiposDto> equiposDtos1 = equipos.stream().map(EquiposDto::of).collect(Collectors.toList());
                return equiposDtos1;
            }

            if (filtradoEquiposDto.numeroGoles()!=0&&filtradoEquiposDto.numeroTitulos()!=0){
                List<Equipos> equiposDtos = equiposRepo.findByGolesTotalesAndTitulosObtenidos(filtradoEquiposDto.numeroGoles(), filtradoEquiposDto.numeroTitulos());
                List<Equipos> equipos = equiposDtos.stream().filter(equipos1 -> equipos1.getDivisiones().getNombre().equalsIgnoreCase("Segunda Division")).collect(Collectors.toList());
                List<EquiposDto> equiposDtos1 = equipos.stream().map(EquiposDto::of).collect(Collectors.toList());
                return equiposDtos1;
            }
        }

    return null;
    }

   public List<EquiposDto> ordenarPorNombre(UUID id){
        Optional<Divisiones> divisiones = divisionesRepo.findById(id);

        if (divisiones.get().getNombre().equalsIgnoreCase("Primera Division")){

            List<Equipos> equipos = equiposRepo.ordenarPorNombre();
            List<Equipos> equipos1 = equipos.stream().filter(equipo1 -> equipo1.getDivisiones().getNombre().equalsIgnoreCase("Primera Division")).collect(Collectors.toList());
            List<EquiposDto> equiposDtos = equipos1.stream().map(EquiposDto::of).collect(Collectors.toList());
            return equiposDtos;
        }else {

            List<Equipos> equipos = equiposRepo.ordenarPorNombre();
            List<Equipos> equipos1 = equipos.stream().filter(equipo1 -> equipo1.getDivisiones().getNombre().equalsIgnoreCase("Segunda Division")).collect(Collectors.toList());
            List<EquiposDto> equiposDtos = equipos1.stream().map(EquiposDto::of).collect(Collectors.toList());
            return equiposDtos;
        }

   }
    public List<EquiposDto> ordenarPorFecha(UUID id){
        Optional<Divisiones> divisiones = divisionesRepo.findById(id);
        if (divisiones.get().getNombre().equalsIgnoreCase("Primera Division")){
            List<Equipos> equipos = equiposRepo.ordenarPorFecha();
            List<Equipos> equipos1 = equipos.stream().filter(equipo1 -> equipo1.getDivisiones().getNombre().equalsIgnoreCase("Primera Division")).collect(Collectors.toList());
            List<EquiposDto> equiposDtos = equipos1.stream().map(EquiposDto::of).collect(Collectors.toList());
            return equiposDtos;
        }else {
            List<Equipos> equipos = equiposRepo.ordenarPorFecha();
            List<Equipos> equipos1 = equipos.stream().filter(equipo1 -> equipo1.getDivisiones().getNombre().equalsIgnoreCase("Segunda Division")).collect(Collectors.toList());
            List<EquiposDto> equiposDtos = equipos1.stream().map(EquiposDto::of).collect(Collectors.toList());
            return equiposDtos;
        }
    }
    public List<EquiposDto> ordenarPorGoles(UUID id){
        Optional<Divisiones> divisiones = divisionesRepo.findById(id);
        if (divisiones.get().getNombre().equalsIgnoreCase("Primera Division")){

            List<Equipos> equipos = equiposRepo.ordenarPorGoles();
            List<Equipos> equipos1 = equipos.stream().filter(equipo1 -> equipo1.getDivisiones().getNombre().equalsIgnoreCase("Primera Division")).collect(Collectors.toList());
            List<EquiposDto> equiposDtos = equipos1.stream().map(EquiposDto::of).collect(Collectors.toList());
            return equiposDtos;
        }else {

            List<Equipos> equipos = equiposRepo.ordenarPorGoles();
            List<Equipos> equipos1 = equipos.stream().filter(equipo1 -> equipo1.getDivisiones().getNombre().equalsIgnoreCase("Segunda Division")).collect(Collectors.toList());
            List<EquiposDto> equiposDtos = equipos1.stream().map(EquiposDto::of).collect(Collectors.toList());
            return equiposDtos;
        }
    }
    public List<EquiposDto> ordenarPorTitulos(UUID id){
        Optional<Divisiones> divisiones = divisionesRepo.findById(id);
        if (divisiones.get().getNombre().equalsIgnoreCase("Primera Division")){
            List<Equipos> equipos = equiposRepo.ordenarPorTitulos();
            List<Equipos> equipos1 = equipos.stream().filter(equipo1 -> equipo1.getDivisiones().getNombre().equalsIgnoreCase("Primera Division")).collect(Collectors.toList());
            List<EquiposDto> equiposDtos = equipos1.stream().map(EquiposDto::of).collect(Collectors.toList());
            return equiposDtos;
        }else {
            List<Equipos> equipos = equiposRepo.ordenarPorTitulos();
            List<Equipos> equipos1 = equipos.stream().filter(equipo1 -> equipo1.getDivisiones().getNombre().equalsIgnoreCase("Segunda Division")).collect(Collectors.toList());
            List<EquiposDto> equiposDtos = equipos1.stream().map(EquiposDto::of).collect(Collectors.toList());
            return equiposDtos;
        }
    }

    public List<EquiposDto> buscar(UUID id,BuscarEquipoDto buscarEquipoDto){
        Optional<Divisiones> divisiones = divisionesRepo.findById(id);
        if (divisiones.get().getNombre().equalsIgnoreCase("Primera Division")){
            List<Equipos> equipos = equiposRepo.findAll();
            List<Equipos> equipos1 = equipos.stream().filter(equipos2 -> equipos2.getNombre().contains(buscarEquipoDto.buscador())
            || equipos2.getCiudad().contains(buscarEquipoDto.buscador())
            || equipos2.getNombreEstadio().contains(buscarEquipoDto.buscador())).collect(Collectors.toList());
            List<Equipos> equipos2 = equipos1.stream().filter(equipo1 -> equipo1.getDivisiones().getNombre().equalsIgnoreCase("Primera Division")).collect(Collectors.toList());
            List<EquiposDto> equiposDtos = equipos2.stream().map(EquiposDto::of).collect(Collectors.toList());
            return equiposDtos;
        }else {
            List<Equipos> equipos = equiposRepo.findAll();
            List<Equipos> equipos1 = equipos.stream().filter(equipos2 -> equipos2.getNombre().contains(buscarEquipoDto.buscador())
                    || equipos2.getCiudad().contains(buscarEquipoDto.buscador())
                    || equipos2.getNombreEstadio().contains(buscarEquipoDto.buscador())).collect(Collectors.toList());
            List<Equipos> equipos2 = equipos1.stream().filter(equipo1 -> equipo1.getDivisiones().getNombre().equalsIgnoreCase("Segunda Division")).collect(Collectors.toList());
            List<EquiposDto> equiposDtos = equipos2.stream().map(EquiposDto::of).collect(Collectors.toList());
            return equiposDtos;
        }
    }




}
