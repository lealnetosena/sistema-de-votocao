package br.com.voto.application.dtos;

import jakarta.validation.constraints.NotNull;

public class SessaoDTO {
    @NotNull
    private Long pautaId;
    private Long tempoMinutos;

    // Getters e Setters
    public Long getPautaId() {
        return pautaId;
    }

    public void setPautaId(Long pautaId) {
        this.pautaId = pautaId;
    }

    public Long getTempoMinutos() {
        return tempoMinutos;
    }

    public void setTempoMinutos(Long tempoMinutos) {
        this.tempoMinutos = tempoMinutos;
    }
}
