package br.com.voto.application.usecase;


import br.com.voto.application.dtos.VotoDTO;
import br.com.voto.domain.model.Pauta;
import br.com.voto.domain.model.Sessao;
import br.com.voto.domain.model.Voto;
import br.com.voto.exception.SessaoError;
import br.com.voto.infra.repository.PautaRepository;
import br.com.voto.infra.repository.SessaoRepository;
import br.com.voto.infra.repository.VotoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service

public class VotoService {

    private final VotoRepository votoRepository;

    private final PautaRepository pautaRepository;

    private final SessaoRepository sessaoRepository;

    public VotoService(VotoRepository votoRepository, PautaRepository pautaRepository, SessaoRepository sessaoRepository) {
        this.votoRepository = votoRepository;
        this.pautaRepository = pautaRepository;
        this.sessaoRepository = sessaoRepository;
    }

    public List<Voto> getAllVotos() {
        return votoRepository.findAll();
    }

    public Optional<Voto> getVotoById(Long id) {
        return votoRepository.findById(id);
    }

    public Voto createVoto(VotoDTO votoDTO) {

        Pauta pauta = pautaRepository.findById(votoDTO.getPautaId())
                .orElseThrow(() -> new SessaoError("Pauta não encontrada com ID: " + votoDTO.getPautaId()));

        Sessao sessao = sessaoRepository.findById(votoDTO.getSessaoId())
                .orElseThrow(() -> new SessaoError("Sessão não encontrada com ID: " + votoDTO.getSessaoId()));

        LocalDateTime agora = LocalDateTime.now();
        if (agora.isBefore(sessao.getInicio())) {
            throw new SessaoError("Sessão ainda não começou");
        } else if (agora.isAfter(sessao.getFim())) {
            throw new SessaoError("Sessão encerrada");
        }

        Voto voto = new Voto();
        voto.setPauta(pauta);
        voto.setSessao(sessao);
        voto.setDataVotacao(agora);
        voto.setVoto(votoDTO.getVoto());

        return votoRepository.save(voto);
    }
    public Voto updateVoto(Long id, VotoDTO updatedVotoDTO) {
        return votoRepository.findById(id)
                .map(existingVoto -> {

                    Sessao sessao = existingVoto.getSessao();
                    LocalDateTime agora = LocalDateTime.now();

                    if (agora.isBefore(sessao.getInicio())) {
                        throw new RuntimeException("Sessão ainda não começou");
                    } else if (agora.isAfter(sessao.getFim())) {
                        throw new RuntimeException("Sessão encerrada");
                    }


                    if (updatedVotoDTO.getPautaId() != null) {
                        existingVoto.setPauta(new Pauta(updatedVotoDTO.getPautaId()));
                    }

                    if (updatedVotoDTO.getVoto() != null) {
                        existingVoto.setVoto(updatedVotoDTO.getVoto());
                    }
                    return votoRepository.save(existingVoto);
                })
                .orElseThrow(() -> new RuntimeException("Voto não encontrado com ID: " + id));
    }

    public void deleteVoto(Long id) {
        votoRepository.deleteById(id);
    }
}
