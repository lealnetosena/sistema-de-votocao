package br.com.voto.infra.repository;



import br.com.voto.domain.model.Sessao;

import java.util.List;
import java.util.Optional;

public interface SessaoRepository {
    List<Sessao> findAll();
    Optional<Sessao> findById(Long id);
    Sessao save(Sessao sessao);
    void deleteById(Long id);
}

