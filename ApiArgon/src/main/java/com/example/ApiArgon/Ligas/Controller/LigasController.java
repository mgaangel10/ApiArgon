package com.example.ApiArgon.Ligas.Controller;

import com.example.ApiArgon.Ligas.Dto.LigasDto;
import com.example.ApiArgon.Ligas.Model.Ligas;
import com.example.ApiArgon.Ligas.Repo.LigasRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class LigasController {

    private final LigasRepo ligasRepo;

    @GetMapping("ligas/all")
    public ResponseEntity<List<LigasDto>> findAll(){
        List<Ligas> ligases = ligasRepo.findAll();
        List<LigasDto> ligasDtos = ligases.stream().map(LigasDto::of).collect(Collectors.toList());
        return ResponseEntity.ok(ligasDtos);
    }
}
