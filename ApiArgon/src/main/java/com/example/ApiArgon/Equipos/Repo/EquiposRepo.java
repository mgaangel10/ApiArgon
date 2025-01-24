package com.example.ApiArgon.Equipos.Repo;

import com.example.ApiArgon.Equipos.Dto.EquiposDto;
import com.example.ApiArgon.Equipos.Model.Equipos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface EquiposRepo extends JpaRepository<Equipos, UUID> {

    List<Equipos> findByGolesTotales(int numeroGoles);
    List<Equipos> findByTitulosObtenidos(int titulos);

    List<Equipos> findByGolesTotalesAndTitulosObtenidos(int goles, int titulos);

    @Query("""
            SELECT e FROM Equipos e ORDER BY e.nombre ASC
            """)
    List<Equipos> ordenarPorNombre();

    @Query("""
            SELECT e FROM Equipos e ORDER BY e.fundado ASC
            """)
    List<Equipos> ordenarPorFecha();

    @Query("""
            SELECT e FROM Equipos e ORDER BY e.golesTotales DESC
            """)
    List<Equipos> ordenarPorGoles();

    @Query("""
            SELECT e FROM Equipos e ORDER BY e.titulosObtenidos DESC
            """)
    List<Equipos> ordenarPorTitulos();
}
