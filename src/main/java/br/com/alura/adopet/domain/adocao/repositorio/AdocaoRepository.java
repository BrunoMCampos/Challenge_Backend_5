package br.com.alura.adopet.domain.adocao.repositorio;

import br.com.alura.adopet.domain.adocao.entidade.Adocao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdocaoRepository extends JpaRepository<Adocao,Long> {
}
