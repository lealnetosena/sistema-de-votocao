package br.com.voto.infra.repository;



import br.com.voto.domain.model.Voto;

import java.util.List;
import java.util.Optional;

public interface VotoRepository {
    List<Voto> findAll();
    Optional<Voto> findById(Long id);
    Voto save(Voto sessao);
    void deleteById(Long id);
}

