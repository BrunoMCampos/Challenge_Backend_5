package br.com.alura.adopet.domain.abrigo.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record DadosAlterarAbrigo(
        @NotBlank(message = "O campo nome é obrigatório!")
        String nome,
        @NotBlank(message = "O campo e-mail é obrigatório!")
        String email,
        @Valid
        DadosEndereco endereco,
        String telefone,
        String celular
) {
}
