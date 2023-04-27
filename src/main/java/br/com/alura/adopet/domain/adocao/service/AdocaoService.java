package br.com.alura.adopet.domain.adocao.service;

import br.com.alura.adopet.controller.MensagemRetorno;
import br.com.alura.adopet.domain.adocao.dto.DadosCadastrarAdocao;
import br.com.alura.adopet.domain.adocao.dto.DadosDetalharAdocao;
import br.com.alura.adopet.domain.adocao.dto.DadosListarAdocoes;
import br.com.alura.adopet.domain.adocao.entidade.Adocao;
import br.com.alura.adopet.domain.adocao.repositorio.AdocaoRepository;
import br.com.alura.adopet.domain.pet.entidade.Pet;
import br.com.alura.adopet.domain.pet.repositorio.PetRepository;
import br.com.alura.adopet.domain.tutor.entidade.Tutor;
import br.com.alura.adopet.domain.tutor.repositorio.TutorRepository;
import br.com.alura.adopet.infra.exception.DadosErroValidacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@Service
public class AdocaoService {

    @Autowired
    private AdocaoRepository adocaoRepository;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private TutorRepository tutorRepository;

    public ResponseEntity<?> cadastrar(DadosCadastrarAdocao dadosAdocao, UriComponentsBuilder uriBuilder) {
        Optional<Pet> optionalPet = petRepository.findById(dadosAdocao.id_pet());
        if (optionalPet.isEmpty()) {
            return ResponseEntity.badRequest().body(new DadosErroValidacao("id_pet", "Pet não encontrado."));
        } else if (optionalPet.get().getAdotado()) {
            return ResponseEntity.badRequest().body(new DadosErroValidacao("id_pet", "Pet já adotado."));
        }
        optionalPet.get().adotar();
        Optional<Tutor> optionalTutor = tutorRepository.findById(dadosAdocao.id_tutor());
        if (optionalTutor.isEmpty()) {
            return ResponseEntity.badRequest().body(new DadosErroValidacao("id_tutor", "Tutor não encontrado."));
        }
        Adocao adocao = adocaoRepository.save(new Adocao(dadosAdocao, optionalPet.get(), optionalTutor.get()));
        URI uri = uriBuilder.path("/adocao/{id}").buildAndExpand(adocao.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalharAdocao(adocao));
    }

    public ResponseEntity<?> listarTodos(Pageable pageable) {
        Page<DadosListarAdocoes> adocoes = adocaoRepository.findAll(pageable).map(DadosListarAdocoes::new);
        if (adocoes.isEmpty()) {
            return ResponseEntity.ok().body(new MensagemRetorno("Nenhuma adoção encontrada."));
        }
        return ResponseEntity.ok().body(adocoes);
    }

    public ResponseEntity<?> detalhar(Long id) {
        Optional<Adocao> optionalAdocao = adocaoRepository.findById(id);
        if (optionalAdocao.isEmpty()) {
            return ResponseEntity.badRequest().body(new DadosErroValidacao("id", "Adoção não encontrada."));
        }
        return ResponseEntity.ok().body(new DadosDetalharAdocao(optionalAdocao.get()));
    }

    public ResponseEntity<?> deletar(Long id) {
        Optional<Adocao> optionalAdocao = adocaoRepository.findById(id);
        if(optionalAdocao.isEmpty()){
            return ResponseEntity.badRequest().body(new DadosErroValidacao("id","Adoção não encontrada."));
        }
        optionalAdocao.get().getPet().cancelarAdocao();
        adocaoRepository.deleteById(id);
        return ResponseEntity.ok().body(new MensagemRetorno("Adoção excluida com sucesso."));
    }
}
