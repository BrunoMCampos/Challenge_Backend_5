package br.com.alura.adopet.domain.abrigo.dto;

import br.com.alura.adopet.domain.abrigo.entidade.Abrigo;
import br.com.alura.adopet.domain.abrigo.entidade.Endereco;

public record DadosListarAbrigos(
        Long id,
        String nome,
        String email,
        Endereco endereco,
        String telefone,
        String celular
) {
    public DadosListarAbrigos(Abrigo abrigo) {
        this(
                abrigo.getId(),
                abrigo.getNome(),
                abrigo.getEmail(),
                abrigo.getEndereco(),
                abrigo.getTelefone(),
                abrigo.getCelular()
        );
    }
}
