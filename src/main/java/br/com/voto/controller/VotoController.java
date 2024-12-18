package br.com.voto.controller;

import br.com.voto.application.dtos.VotoDTO;
import br.com.voto.application.usecase.VotoService;
import br.com.voto.domain.model.Voto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/voto")

public class VotoController {
    @Autowired
    private final VotoService votoService;

    public VotoController(VotoService votoService) {
        this.votoService = votoService;
    }

    @GetMapping
    public List<Voto> getAllVotos() {
        return votoService.getAllVotos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Voto> getVotoById(@PathVariable Long id) {
        return votoService.getVotoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Voto createVoto(@Valid @RequestBody VotoDTO voto) {
        return votoService.createVoto(voto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Voto> updateVoto(@PathVariable Long id, @RequestBody VotoDTO votoDTO) {
        try {
            return ResponseEntity.ok(votoService.updateVoto(id, votoDTO));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVoto(@PathVariable Long id) {
        votoService.deleteVoto(id);
        return ResponseEntity.noContent().build();
    }
}


