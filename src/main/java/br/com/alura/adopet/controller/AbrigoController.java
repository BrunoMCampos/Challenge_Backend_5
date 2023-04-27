package br.com.alura.adopet.controller;

import br.com.alura.adopet.domain.abrigo.dto.*;
import br.com.alura.adopet.domain.abrigo.entidade.Abrigo;
import br.com.alura.adopet.domain.abrigo.repositorio.AbrigoRepository;
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
@RequestMapping("abrigos")
public class AbrigoController {

    @Autowired
    private AbrigoRepository abrigoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosAbrigoCriado> cadastrar(@RequestBody @Valid DadosCadastrarAbrigo dadosAbrigo, UriComponentsBuilder uriBuilder) {
        Abrigo abrigo = abrigoRepository.save(new Abrigo(dadosAbrigo));
        URI uri = uriBuilder.path("/abrigos/{id}").buildAndExpand(abrigo.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosAbrigoCriado(abrigo));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> detalhar(@PathVariable Long id) {
        Optional<Abrigo> optionalAbrigo = abrigoRepository.findById(id);
        if (optionalAbrigo.isEmpty()) {
            return ResponseEntity.badRequest().body(new DadosErroValidacao("id", "Abrigo não encontrado."));
        }
        return ResponseEntity.ok().body(new DadosDetalharAbrigo(optionalAbrigo.get()));
    }

    @GetMapping
    public ResponseEntity<?> exibirTodos(Pageable pageable) {
        Page<DadosListarAbrigos> abrigos = abrigoRepository.findAll(pageable).map(DadosListarAbrigos::new);
        if (abrigos.isEmpty()) {
            return ResponseEntity.ok().body(new MensagemRetorno("Nenhum abrigo encontrado."));
        }
        return ResponseEntity.ok().body(abrigos);
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<?> exibirTodos(@PathVariable Long id) {
        Optional<Abrigo> optionalAbrigo = abrigoRepository.findById(id);
        if (optionalAbrigo.isEmpty()) {
            return ResponseEntity.badRequest().body(new DadosErroValidacao("id", "Abrigo não encontrado."));
        }
        abrigoRepository.deleteById(id);
        return ResponseEntity.ok().body(new MensagemRetorno("Abrigo excluído com sucesso."));
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<?> atualizarPut(@PathVariable Long id, @RequestBody @Valid DadosAlterarAbrigo dadosAbrigo) {
        return atualizarAbrigo(id, dadosAbrigo);
    }

    @PatchMapping("{id}")
    @Transactional
    public ResponseEntity<?> atualizarPatch(@PathVariable Long id, @RequestBody @Valid DadosAlterarAbrigo dadosAbrigo) {
        return atualizarAbrigo(id, dadosAbrigo);
    }

    private ResponseEntity<?> atualizarAbrigo(Long id, DadosAlterarAbrigo dadosAbrigo) {
        Optional<Abrigo> optionalAbrigo = abrigoRepository.findById(id);
        if (optionalAbrigo.isEmpty()) {
            return ResponseEntity.badRequest().body(new DadosErroValidacao("id", "Abrigo não encontrado."));
        }
        optionalAbrigo.get().atualizar(dadosAbrigo);
        return ResponseEntity.ok().body(new DadosDetalharAbrigo(optionalAbrigo.get()));
    }

}
