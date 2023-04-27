package br.com.alura.adopet.controller;

import br.com.alura.adopet.domain.adocao.dto.DadosCadastrarAdocao;
import br.com.alura.adopet.domain.adocao.service.AdocaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/adocao")
public class AdocaoController {

    @Autowired
    private AdocaoService adocaoService;

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid DadosCadastrarAdocao dadosAdocao, UriComponentsBuilder uriBuilder) {
        return adocaoService.cadastrar(dadosAdocao, uriBuilder);
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        return adocaoService.deletar(id);
    }

    @GetMapping
    public ResponseEntity<?> listarTodos(Pageable pageable) {
        return adocaoService.listarTodos(pageable);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> detalhar(@PathVariable Long id) {
        return adocaoService.detalhar(id);
    }
}