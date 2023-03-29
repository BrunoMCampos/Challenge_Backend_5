package domain.tutor;

import br.com.alura.adopet.domain.tutor.Tutor;

public record DadosListarTutores(Long id, String nome, String email, String urlFoto, String telefone, String cidade,
                                 String sobre) {
    public DadosListarTutores(Tutor tutor) {
        this(tutor.getId(), tutor.getNome(), tutor.getEmail(), tutor.getUrlFoto(), tutor.getTelefone(), tutor.getCidade(), tutor.getSobre());
    }
}
