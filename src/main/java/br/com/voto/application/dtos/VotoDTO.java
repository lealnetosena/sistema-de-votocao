package br.com.voto.application.dtos;

import br.com.voto.domain.model.enums.EVotos;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class VotoDTO {
    @NotNull
    private Long pautaId;
    private Long tempoMinutos;
    private Long sessaoId;
    private LocalDateTime dataVotacao;
    private EVotos voto;

    public @NotNull Long getPautaId() {
        return pautaId;
    }

    public void setPautaId(@NotNull Long pautaId) {
        this.pautaId = pautaId;
    }

    public Long getTempoMinutos() {
        return tempoMinutos;
    }

    public void setTempoMinutos(Long tempoMinutos) {
        this.tempoMinutos = tempoMinutos;
    }

    public Long getSessaoId() {
        return sessaoId;
    }

    public void setSessaoId(Long sessaoId) {
        this.sessaoId = sessaoId;
    }

    public LocalDateTime getDataVotacao() {
        return dataVotacao;
    }

    public void setDataVotacao(LocalDateTime dataVotacao) {
        this.dataVotacao = dataVotacao;
    }

    public EVotos getVoto() {
        return voto;
    }

    public void setVoto(EVotos voto) {
        this.voto = voto;
    }
}
