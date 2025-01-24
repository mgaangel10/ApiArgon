package com.example.ApiArgon.Divisiones.Repo;

import com.example.ApiArgon.Divisiones.Model.Divisiones;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface DivisionesRepo extends JpaRepository<Divisiones, UUID> {

    Optional<Divisiones> findByNombre(String nombre);
}
