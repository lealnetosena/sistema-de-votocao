package br.com.voto.domain.model;

import br.com.voto.domain.model.enums.EVotos;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table
public class Voto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Pauta pauta;
    @ManyToOne
    private Sessao sessao;
    private LocalDateTime dataVotacao;
    private EVotos voto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pauta getPauta() {
        return pauta;
    }

    public void setPauta(Pauta pauta) {
        this.pauta = pauta;
    }

    public Sessao getSessao() {
        return sessao;
    }

    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
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
