package com.example.ApiArgon.Ligas.Repo;

import com.example.ApiArgon.Ligas.Model.Ligas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LigasRepo extends JpaRepository<Ligas, UUID> {
}
