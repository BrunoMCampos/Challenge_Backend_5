package br.com.alura.adopet.domain.adocao.entidade;

import br.com.alura.adopet.domain.adocao.dto.DadosCadastrarAdocao;
import br.com.alura.adopet.domain.pet.entidade.Pet;
import br.com.alura.adopet.domain.tutor.entidade.Tutor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity(name = "Adocao")
@Table(name = "adocao")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Adocao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "id_pet")
    @OneToOne
    private Pet pet;

    @JoinColumn(name = "id_tutor")
    @ManyToOne
    private Tutor tutor;

    private LocalDate data;

    public Adocao(DadosCadastrarAdocao dadosAdocao, Pet pet, Tutor tutor) {
        this.pet = pet;
        this.tutor = tutor;
        this.data = dadosAdocao.data();
    }
}
