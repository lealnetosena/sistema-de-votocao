package br.com.voto.application.usecase;

import br.com.voto.domain.model.Pauta;
import br.com.voto.infra.repository.PautaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class PautaService {

    private final PautaRepository pautaRepository;

    // Construtor para injeção de dependência
    public PautaService(PautaRepository pautaRepository) {
        this.pautaRepository = pautaRepository;
    }

    public List<Pauta> getAllPautas() {
        return pautaRepository.findAll();
    }

    public Optional<Pauta> getPautaById(Long id) {
        return pautaRepository.findById(id);
    }

    public Pauta createPauta(Pauta pauta) {
        return pautaRepository.save(pauta);
    }

    public Pauta updatePauta(Long id, Pauta updatedPauta) {
        return pautaRepository.findById(id)
                .map(existingPauta -> {
                    existingPauta.setTitle(updatedPauta.getTitle());
                    existingPauta.setCompleted(updatedPauta.isCompleted());
                    return pautaRepository.save(existingPauta);
                }).orElseThrow(() -> new RuntimeException("Pauta not found"));
    }

    public void deletePauta(Long id) {
        pautaRepository.deleteById(id);
    }
}

