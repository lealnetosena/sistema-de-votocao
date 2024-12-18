package br.com.voto.infra.repository;

import br.com.voto.domain.model.Sessao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessaoJpaRepository extends JpaRepository<Sessao, Long>, SessaoRepository {
}

