package br.com.alura.adopet.domain.tutor.dto;

import br.com.alura.adopet.domain.tutor.entidade.Tutor;

public record DadosTutorCriado(
        String email,
        String nome,
        String senha
) {
    public DadosTutorCriado(Tutor tutor) {
        this(tutor.getEmail(), tutor.getNome(), tutor.getSenha());
    }
}
