package br.com.voto.domain.model.enums;

public enum EVotos {

    SIM(0),
    NAO(1);

    private final int valor;

    EVotos(int valor) {
        this.valor = valor;
    }

    public int getValor() {
            return valor;
        }
}