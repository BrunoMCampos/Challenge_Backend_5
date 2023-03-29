package br.com.alura.adopet.domain.tutor.validacao;

public class ValidacaoException extends RuntimeException {
    public ValidacaoException(String mensagem) {
        super(mensagem);
    }
}
