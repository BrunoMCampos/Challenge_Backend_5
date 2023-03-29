package br.com.alura.adopet.domain.tutor;

import domain.tutor.DadosAlterarTutor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tutor")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Tutor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String senha;
    private String urlFoto;
    private String telefone;
    private String cidade;
    private String sobre;

    /*
     * Construtor apenas para cadastro de novo usuário!
     * */

    public Tutor(DadosCadastrarTutor dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.senha = dados.senha();
    }

    public void alterar(DadosAlterarTutor dados) {
        if(dados.nome() != null){
            this.nome = dados.nome();
        }
        if(dados.cidade() != null){
            this.cidade = dados.cidade();
        }
        if(dados.urlFoto() != null){
            this.urlFoto = dados.urlFoto();
        }
        if(dados.sobre() != null){
            this.sobre = dados.sobre();
        }
        if(dados.telefone() != null){
            this.telefone = dados.telefone();
        }
    }
}
