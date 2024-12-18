package br.com.voto.infra.repository;

import br.com.voto.domain.model.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotoJpaRepository extends JpaRepository<Voto, Long>, VotoRepository {
}

