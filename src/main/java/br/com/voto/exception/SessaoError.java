package br.com.voto.exception;

public class SessaoError extends RuntimeException {
    public SessaoError(String message) {
        super(message);
    }
}