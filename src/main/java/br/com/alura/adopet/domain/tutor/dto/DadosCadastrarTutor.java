package br.com.alura.adopet.domain.tutor.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DadosCadastrarTutor(
        @NotBlank(message = "E-mail é um campo obrigatório.")
        @Email
        String email,
        @NotBlank(message = "Nome é um campo obrigatório.")
        String nome,
        @NotBlank(message = "Senha é um campo obrigatório.")
        String senha
) {
}
