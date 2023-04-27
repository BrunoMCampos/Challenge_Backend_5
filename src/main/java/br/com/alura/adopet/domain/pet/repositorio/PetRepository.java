package br.com.alura.adopet.domain.pet.repositorio;

import br.com.alura.adopet.domain.pet.entidade.Pet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet,Long> {

    Page<Pet> findAllByAdotadoFalse(Pageable pageable);

}
