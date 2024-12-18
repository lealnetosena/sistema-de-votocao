package br.com.voto.infra.repository;



import br.com.voto.domain.model.Pauta;

import java.util.List;
import java.util.Optional;

public interface PautaRepository {
    List<Pauta> findAll();
    Optional<Pauta> findById(Long id);
    Pauta save(Pauta pauta);
    void deleteById(Long id);
}

