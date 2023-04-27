package br.com.alura.adopet.domain.tutor.dto;

public record DadosAlterarTutor(
        String nome,
        String urlFoto,
        String telefone,
        String cidade,
        String sobre
) {
}
