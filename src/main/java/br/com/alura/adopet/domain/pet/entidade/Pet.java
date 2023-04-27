package br.com.alura.adopet.domain.pet.entidade;

import br.com.alura.adopet.domain.abrigo.entidade.Abrigo;
import br.com.alura.adopet.domain.pet.dto.DadosAlterarPet;
import br.com.alura.adopet.domain.pet.dto.DadosCadastrarPet;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Pet")
@Table(name = "pet")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String idade;

    @Enumerated(EnumType.STRING)
    private Porte porte;

    private String caracteristicas;

    @JoinColumn(name = "id_abrigo")
    @ManyToOne
    private Abrigo abrigo;

    private String url_foto;
    private Boolean adotado;

    public Pet(DadosCadastrarPet dadosPet, Abrigo abrigo) {
        this.nome = dadosPet.nome();
        this.idade = dadosPet.idade();
        this.porte = dadosPet.porte();
        this.caracteristicas = dadosPet.caracteristicas();
        this.abrigo = abrigo;
        this.url_foto = dadosPet.url_foto();
        this.adotado = dadosPet.adotado();
    }

    public void alterar(DadosAlterarPet dadosPet, Abrigo abrigo) {
        this.nome = dadosPet.nome();
        this.idade = dadosPet.idade();
        this.porte = dadosPet.porte();
        this.caracteristicas = dadosPet.caracteristicas();
        this.abrigo = abrigo;
        this.url_foto = dadosPet.url_foto();
        this.adotado = dadosPet.adotado();
    }

    public void adotar(){
        if(this.adotado){
            throw new RuntimeException("Pet já adotado!");
        }
        this.adotado = true;
    }

    public void cancelarAdocao(){
        if(!this.adotado){
            throw new RuntimeException("Pet ainda não adotado!");
        }
        this.adotado = false;
    }
}
