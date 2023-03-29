package br.com.alura.adopet.domain.tutor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DadosCadastrarTutor(
        @NotBlank
        @Email
        String email,
        @NotBlank
        String nome,
        @NotBlank
        String senha
) {
}
