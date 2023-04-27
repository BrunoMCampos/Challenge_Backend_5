package br.com.alura.adopet.domain.abrigo.entidade;

import br.com.alura.adopet.domain.abrigo.dto.DadosEndereco;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Endereco {
    private String estado;
    private String cidade;
    private String bairro;
    private String rua;
    private String numero;

    public Endereco(DadosEndereco endereco) {
        this.estado = endereco.estado();
        this.cidade = endereco.cidade();
        this.bairro = endereco.bairro();
        this.rua = endereco.rua();
        this.numero = endereco.numero();
    }
}
