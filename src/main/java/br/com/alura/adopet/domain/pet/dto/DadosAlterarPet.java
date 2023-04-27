package br.com.alura.adopet.domain.pet.dto;

import br.com.alura.adopet.domain.pet.entidade.Porte;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAlterarPet(
        @NotBlank(message = "O campo nome é obrigatório!")
        String nome,
        @NotBlank(message = "O campo idade é obrigatório!")
        String idade,
        @NotNull(message = "O campo porte é obrigatório!")
        Porte porte,
        @NotBlank(message = "O campo caracteristicas é obrigatório!")
        String caracteristicas,
        @NotNull(message = "O campo id_abrigo é obrigatório!")
        Long id_abrigo,
        @NotNull(message = "O campo url_foto é obrigatório!")
        String url_foto,
        @NotNull(message = "O campo adotado é obrigatório!")
        Boolean adotado
) {
}
