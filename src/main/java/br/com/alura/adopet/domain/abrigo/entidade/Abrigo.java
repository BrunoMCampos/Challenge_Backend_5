package br.com.alura.adopet.domain.abrigo.entidade;

import br.com.alura.adopet.domain.abrigo.dto.DadosAlterarAbrigo;
import br.com.alura.adopet.domain.abrigo.dto.DadosCadastrarAbrigo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Abrigo")
@Table(name = "abrigo")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Abrigo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;

    @Embedded
    private Endereco endereco;
    private String telefone;
    private String celular;

    public Abrigo(DadosCadastrarAbrigo dadosAbrigo) {
        this.nome = dadosAbrigo.nome();
        this.email = dadosAbrigo.email();
        this.endereco = new Endereco(dadosAbrigo.endereco());
        this.telefone = dadosAbrigo.telefone();
        this.celular = dadosAbrigo.celular();
    }

    public void atualizar(DadosAlterarAbrigo dadosAbrigo) {
        this.nome = dadosAbrigo.nome();
        this.email = dadosAbrigo.email();
        this.endereco = new Endereco(dadosAbrigo.endereco());
        this.telefone = dadosAbrigo.telefone();
        this.celular = dadosAbrigo.celular();
    }
}
