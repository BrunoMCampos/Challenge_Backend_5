package br.com.alura.adopet.domain.abrigo.repositorio;

import br.com.alura.adopet.domain.abrigo.entidade.Abrigo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AbrigoRepository extends JpaRepository<Abrigo,Long> {
}
