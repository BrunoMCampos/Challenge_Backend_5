package br.com.alura.adopet.domain.pet.dto;

import br.com.alura.adopet.domain.abrigo.entidade.Abrigo;
import br.com.alura.adopet.domain.pet.entidade.Pet;
import br.com.alura.adopet.domain.pet.entidade.Porte;

public record DadosDetalharPet(
        Long id,
        String nome,
        String idade,
        Porte porte,
        String caracteristicas,
        Long id_abrigo,
        String url_foto,
        Boolean adotado
) {
    public DadosDetalharPet(Pet pet) {
        this(
                pet.getId(),
                pet.getNome(),
                pet.getIdade(),
                pet.getPorte(),
                pet.getCaracteristicas(),
                pet.getAbrigo().getId(),
                pet.getUrl_foto(),
                pet.getAdotado()
        );
    }
}
