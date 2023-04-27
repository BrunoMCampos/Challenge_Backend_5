package br.com.alura.adopet.domain.adocao.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record DadosCadastrarAdocao(
        @NotNull(message = "O campo id_pet é obrigatório!")
        Long id_pet,
        @NotNull(message = "O campo id_tutor é obrigatório!")
        Long id_tutor,
        @NotNull(message = "O campo data é obrigatório!")
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate data
) {
}
