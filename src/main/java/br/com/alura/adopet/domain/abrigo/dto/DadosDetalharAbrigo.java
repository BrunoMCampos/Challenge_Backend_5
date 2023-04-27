package br.com.alura.adopet.domain.abrigo.dto;

import br.com.alura.adopet.domain.abrigo.entidade.Abrigo;

public record DadosDetalharAbrigo(
        Long id,
        String nome,
        String email,
        DadosEndereco endereco,
        String telefone,
        String celular
) {
    public DadosDetalharAbrigo(Abrigo abrigo) {
        this(
                abrigo.getId(),
                abrigo.getNome(),
                abrigo.getEmail(),
                new DadosEndereco(abrigo.getEndereco()),
                abrigo.getTelefone(),
                abrigo.getCelular()
        );
    }
}
