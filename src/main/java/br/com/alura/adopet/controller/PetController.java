package br.com.alura.adopet.controller;

import br.com.alura.adopet.domain.abrigo.entidade.Abrigo;
import br.com.alura.adopet.domain.abrigo.repositorio.AbrigoRepository;
import br.com.alura.adopet.domain.pet.dto.DadosAlterarPet;
import br.com.alura.adopet.domain.pet.dto.DadosCadastrarPet;
import br.com.alura.adopet.domain.pet.dto.DadosDetalharPet;
import br.com.alura.adopet.domain.pet.dto.DadosListarPets;
import br.com.alura.adopet.domain.pet.entidade.Pet;
import br.com.alura.adopet.domain.pet.repositorio.PetRepository;
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
import java.util.stream.Stream;

@RestController
@RequestMapping("pets")
public class PetController {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private AbrigoRepository abrigoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid DadosCadastrarPet dadosPet, UriComponentsBuilder uriBuilder) {
        Optional<Abrigo> optionalAbrigo = abrigoRepository.findById(dadosPet.id_abrigo());
        if (optionalAbrigo.isEmpty()) {
            return ResponseEntity.badRequest().body(new DadosErroValidacao("id_abrigo", "Abrigo não encontrado."));
        }
        Pet petCriado = petRepository.save(new Pet(dadosPet, optionalAbrigo.get()));
        URI uri = uriBuilder.path("/pets/{id}").buildAndExpand(petCriado.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalharPet(petCriado));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> detalhar(@PathVariable Long id) {
        Optional<Pet> optionalPet = petRepository.findById(id);
        if (optionalPet.isEmpty()) {
            return ResponseEntity.badRequest().body(new DadosErroValidacao("id", "Pet não encontrado."));
        }
        return ResponseEntity.ok().body(new DadosDetalharPet(optionalPet.get()));
    }

    @GetMapping
    public ResponseEntity<?> listarTodos(Pageable pageable) {
        Page<DadosListarPets> pets = petRepository.findAllByAdotadoFalse(pageable).map(DadosListarPets::new);
        if (pets.isEmpty()) {
            return ResponseEntity.ok().body(new MensagemRetorno("Nenhum pet encontrado."));
        }
        return ResponseEntity.ok().body(pets);
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<?> alterarPut(@PathVariable Long id, @RequestBody @Valid DadosAlterarPet dadosPet) {
        return putOrPatch(id, dadosPet);
    }

    @PatchMapping("{id}")
    @Transactional
    public ResponseEntity<?> alterarPatch(@PathVariable Long id, @RequestBody @Valid DadosAlterarPet dadosPet) {
        return putOrPatch(id, dadosPet);
    }

    private ResponseEntity<? extends Record> putOrPatch(Long id, DadosAlterarPet dadosPet) {
        Optional<Pet> optionalPet = petRepository.findById(id);
        if (optionalPet.isEmpty()) {
            return ResponseEntity.badRequest().body(new DadosErroValidacao("id", "Pet não encontrado."));
        }
        Optional<Abrigo> optionalAbrigo = abrigoRepository.findById(dadosPet.id_abrigo());
        if (optionalAbrigo.isEmpty()) {
            return ResponseEntity.badRequest().body(new DadosErroValidacao("id_abrigo", "Abrigo não encontrado."));
        }
        optionalPet.get().alterar(dadosPet, optionalAbrigo.get());
        return ResponseEntity.ok().body(new DadosDetalharPet(optionalPet.get()));
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        Optional<Pet> optionalPet = petRepository.findById(id);
        if (optionalPet.isEmpty()) {
            return ResponseEntity.badRequest().body(new DadosErroValidacao("id", "Pet não encontrado."));
        }
        petRepository.deleteById(id);
        return ResponseEntity.ok().body(new MensagemRetorno("Pet excluído com sucesso."));
    }
}
