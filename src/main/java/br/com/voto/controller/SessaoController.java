package br.com.voto.controller;

import br.com.voto.application.dtos.SessaoDTO;
import br.com.voto.application.usecase.SessaoService;
import br.com.voto.domain.model.Sessao;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sessao")

public class SessaoController {
    @Autowired
    private final SessaoService sessaoService;

    public SessaoController(SessaoService sessaoService) {
        this.sessaoService = sessaoService;
    }

    @GetMapping
    public List<Sessao> getAllSessaos() {
        return sessaoService.getAllSessaos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sessao> getSessaoById(@PathVariable Long id) {
        return sessaoService.getSessaoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Sessao createSessao(@Valid @RequestBody SessaoDTO sessao) {
        return sessaoService.createSessao(sessao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sessao> updateSessao(@PathVariable Long id, @RequestBody SessaoDTO sessaoDTO) {
        try {
            return ResponseEntity.ok(sessaoService.updateSessao(id, sessaoDTO));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSessao(@PathVariable Long id) {
        sessaoService.deleteSessao(id);
        return ResponseEntity.noContent().build();
    }
}


