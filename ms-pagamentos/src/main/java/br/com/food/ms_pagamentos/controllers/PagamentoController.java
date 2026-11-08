package br.com.food.ms_pagamentos.controllers;


import br.com.food.ms_pagamentos.dto.PagamentoDTO;
import br.com.food.ms_pagamentos.model.Pagamento;
import br.com.food.ms_pagamentos.services.PagamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/pagamentos")
@RequiredArgsConstructor
public class PagamentoController {

    private final PagamentoService pagamentoService;


    @GetMapping
    public ResponseEntity<List<PagamentoDTO>> listar() {
        var pagamentosList = pagamentoService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(pagamentosList);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<PagamentoDTO> findById(@PathVariable("id") @NotNull Long id) {
        var pagamentosId = pagamentoService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(pagamentosId);
    }

    @PostMapping
    public ResponseEntity<PagamentoDTO> salvar(
            @RequestBody @Valid PagamentoDTO dto,
            UriComponentsBuilder uriBuilder) {

        PagamentoDTO pagamentoCriado = pagamentoService.createPagamento(dto);

        var uri = uriBuilder
                .path("/pagamentos/{id}")
                .buildAndExpand(pagamentoCriado.id())
                .toUri();

        return ResponseEntity.created(uri).body(pagamentoCriado);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<PagamentoDTO> atualizar(@PathVariable("id") Long id,
                                                   @RequestBody @Valid PagamentoDTO dto) {

        var pagamentoAtualziado = pagamentoService.updatePagamento(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(pagamentoAtualziado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable("id") Long id) {

        pagamentoService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}