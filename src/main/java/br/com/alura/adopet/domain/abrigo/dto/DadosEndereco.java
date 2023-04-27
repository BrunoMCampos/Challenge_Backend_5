package br.com.alura.adopet.domain.abrigo.dto;

import br.com.alura.adopet.domain.abrigo.entidade.Endereco;
import jakarta.validation.constraints.NotBlank;

public record DadosEndereco(
        @NotBlank(message = "O campo estado é obrigatório!")
        String estado,
        @NotBlank(message = "O campo cidade é obrigatório!")
        String cidade,
        @NotBlank(message = "O campo bairro é obrigatório!")
        String bairro,
        @NotBlank(message = "O campo rua é obrigatório!")
        String rua,

        String numero
) {
    public DadosEndereco(Endereco endereco) {
        this(
                endereco.getEstado(),
                endereco.getCidade(),
                endereco.getBairro(),
                endereco.getRua(),
                endereco.getNumero()
        );
    }
}
