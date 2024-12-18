package br.com.voto.infra.repository;

import br.com.voto.domain.model.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PautaJpaRepository extends JpaRepository<Pauta, Long>, PautaRepository {
}

