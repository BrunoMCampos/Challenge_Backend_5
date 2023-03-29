package br.com.alura.adopet.domain.tutor;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TutorRepository extends JpaRepository<Tutor, Long> {

    Boolean existsByEmail(String email);

}
