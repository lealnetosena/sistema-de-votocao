package br.com.voto.application.usecase;

import br.com.voto.application.dtos.SessaoDTO;
import br.com.voto.domain.model.Pauta;
import br.com.voto.domain.model.Sessao;
import br.com.voto.infra.repository.PautaRepository;
import br.com.voto.infra.repository.SessaoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service

public class SessaoService {

    private final SessaoRepository sessaoRepository;

    private final PautaRepository pautaRepository;
    // Construtor para injeção de dependência
    public SessaoService(SessaoRepository sessaoRepository, PautaRepository pautaRepository) {
        this.sessaoRepository = sessaoRepository;
        this.pautaRepository = pautaRepository;
    }

    public List<Sessao> getAllSessaos() {
        return sessaoRepository.findAll();
    }

    public Optional<Sessao> getSessaoById(Long id) {
        return sessaoRepository.findById(id);
    }

    public Sessao createSessao(SessaoDTO sessaoDTO) {
        Pauta pauta = pautaRepository.findById(sessaoDTO.getPautaId())
                .orElseThrow(() -> new RuntimeException("Pauta não encontrada com ID: " + sessaoDTO.getPautaId()));

        Sessao sessao = new Sessao();
        sessao.setPauta(pauta);
        sessao.setInicio(LocalDateTime.now());
        sessao.setFim(sessao.getInicio().plusMinutes(sessaoDTO.getTempoMinutos()));

        return sessaoRepository.save(sessao);
    }

    public Sessao updateSessao(Long id, SessaoDTO updatedSessaoDTO) {
        return sessaoRepository.findById(id)
                .map(existingSessao -> {
                    if (updatedSessaoDTO.getPautaId() != null) {
                        existingSessao.setPauta(new Pauta(updatedSessaoDTO.getPautaId()));
                    }
                    if (updatedSessaoDTO.getTempoMinutos() != null) {
                        existingSessao.setFim(existingSessao.getInicio().plusMinutes(updatedSessaoDTO.getTempoMinutos()));
                    }
                    return sessaoRepository.save(existingSessao);
                }).orElseThrow(() -> new RuntimeException("Sessão não encontrada"));
    }

    public void deleteSessao(Long id) {
        sessaoRepository.deleteById(id);
    }
}

