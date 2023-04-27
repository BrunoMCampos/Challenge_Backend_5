package br.com.alura.adopet.domain.tutor.repositorio;

import br.com.alura.adopet.domain.tutor.entidade.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TutorRepository extends JpaRepository<Tutor, Long> {

    Boolean existsByEmail(String email);

}
