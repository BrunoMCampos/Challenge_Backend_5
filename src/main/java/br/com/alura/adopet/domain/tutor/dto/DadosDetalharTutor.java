package br.com.alura.adopet.domain.tutor.dto;

import br.com.alura.adopet.domain.tutor.entidade.Tutor;

public record DadosDetalharTutor(
        String urlFoto,
        String nome,
        String telefone,
        String cidade,
        String sobre
) {
    public DadosDetalharTutor(Tutor tutor){
        this(tutor.getUrlFoto(), tutor.getNome(),tutor.getTelefone(), tutor.getCidade(), tutor.getSobre());
    }
}
