package br.com.alura.adopet.controller;

import br.com.alura.adopet.domain.tutor.dto.*;
import br.com.alura.adopet.domain.tutor.entidade.Tutor;
import br.com.alura.adopet.domain.tutor.repositorio.TutorRepository;
import br.com.alura.adopet.infra.exception.DadosErroValidacao;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/tutores")
public class TutorController {

    @Autowired
    private TutorRepository tutorRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrarTutor(@RequestBody @Valid DadosCadastrarTutor dados, UriComponentsBuilder uriBuilder) {
        if (tutorRepository.existsByEmail(dados.email())) {
            return ResponseEntity.badRequest().body(new DadosErroValidacao("email", "E-mail já cadastrado."));
        } else {
            Tutor tutor = tutorRepository.save(new Tutor(dados));
            URI uri = uriBuilder.path("tutor/{idTutor}").buildAndExpand(tutor.getId()).toUri();
            return ResponseEntity.created(uri).body(new DadosTutorCriado(tutor));
        }
    }

    @GetMapping
    public ResponseEntity<?> listarTodos(Pageable pageable) {
        Page<DadosListarTutores> page = tutorRepository.findAll(pageable).map(DadosListarTutores::new);
        if (page.isEmpty()) {
            return ResponseEntity.ok().body(new MensagemRetorno("Nenhum tutor encontrado."));
        } else {
            return ResponseEntity.ok().body(page);
        }
    }

    @GetMapping("/{idTutor}")
    public ResponseEntity<?> detalharTutor(@PathVariable Long idTutor) {
        Optional<Tutor> optionalTutor = tutorRepository.findById(idTutor);
        if (optionalTutor.isEmpty()) {
            return ResponseEntity.badRequest().body(new DadosErroValidacao("id", "Tutor não encontrado."));
        } else {
            Tutor tutor = optionalTutor.get();
            return ResponseEntity.ok().body(new DadosDetalharTutor(tutor));
        }
    }

    @PutMapping("/{idTutor}")
    @Transactional
    public ResponseEntity<?> alterarTutorPut(@PathVariable Long idTutor, @RequestBody DadosAlterarTutor dados) {
        return putOuPatch(idTutor, dados);
    }

    @PatchMapping("/{idTutor}")
    @Transactional
    public ResponseEntity<?> alterarTutorPatch(@PathVariable Long idTutor, @RequestBody DadosAlterarTutor dados) {
        return putOuPatch(idTutor, dados);
    }

    private ResponseEntity<?> putOuPatch(Long idTutor, DadosAlterarTutor dados) {
        Optional<Tutor> optionalTutor = tutorRepository.findById(idTutor);
        if (optionalTutor.isEmpty()) {
            return ResponseEntity.badRequest().body(new DadosErroValidacao("id", "Tutor não encontrado"));
        } else {
            Tutor tutor = optionalTutor.get();
            tutor.alterar(dados);
            return ResponseEntity.ok().body(new DadosDetalharTutor(tutor));
        }
    }

    @DeleteMapping("{idTutor}")
    @Transactional
    public ResponseEntity<?> deletar(@PathVariable Long idTutor) {
        Optional<Tutor> optionalTutor = tutorRepository.findById(idTutor);
        if (optionalTutor.isEmpty()) {
            return ResponseEntity.badRequest().body(new DadosErroValidacao("id", "Tutor não encontrado"));
        }
        tutorRepository.deleteById(idTutor);
        return ResponseEntity.ok().build();
    }

}
