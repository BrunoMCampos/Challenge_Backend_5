package br.com.alura.adopet.domain.adocao.dto;

import br.com.alura.adopet.domain.adocao.entidade.Adocao;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record DadosListarAdocoes(
        Long id,
        Long id_pet,
        Long id_tutor,
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate data
) {
    public DadosListarAdocoes(Adocao adocao) {
        this(
                adocao.getId(),
                adocao.getPet().getId(),
                adocao.getTutor().getId(),
                adocao.getData()
        );
    }
}
