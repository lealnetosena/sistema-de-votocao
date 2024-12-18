package br.com.voto.controller;

import br.com.voto.application.usecase.PautaService;
import br.com.voto.domain.model.Pauta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pautas")

public class PautaController {
    @Autowired
    private final PautaService pautaService;

    public PautaController(PautaService pautaService) {
        this.pautaService = pautaService;
    }

    @GetMapping
    public List<Pauta> getAllPautas() {
        return pautaService.getAllPautas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pauta> getPautaById(@PathVariable Long id) {
        return pautaService.getPautaById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Pauta createPauta(@RequestBody Pauta pauta) {
        return pautaService.createPauta(pauta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pauta> updatePauta(@PathVariable Long id, @RequestBody Pauta pauta) {
        try {
            return ResponseEntity.ok(pautaService.updatePauta(id, pauta));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePauta(@PathVariable Long id) {
        pautaService.deletePauta(id);
        return ResponseEntity.noContent().build();
    }
}

