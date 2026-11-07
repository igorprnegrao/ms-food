package br.com.food.ms_pagamentos.services;


import br.com.food.ms_pagamentos.dto.PagamentoDTO;
import br.com.food.ms_pagamentos.model.Pagamento;
import br.com.food.ms_pagamentos.model.Status;
import br.com.food.ms_pagamentos.repositories.PagamentosRepository;
import lombok.RequiredArgsConstructor;
import br.com.food.ms_pagamentos.mapper.PagamentoMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PagamentoService {

    private final PagamentosRepository pagamentosRepository;

    private final PagamentoMapper pagamentoMapper;


    public List<PagamentoDTO> getAll(){
        return pagamentosRepository.findAll()
                .stream()
                .map(pagamentoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PagamentoDTO getById(Long id) {
        Optional<Pagamento> optionalPagamento = pagamentosRepository.findById(id);

        Pagamento pagamento = optionalPagamento.orElseThrow(EntityNotFoundException::new);

        return pagamentoMapper.toDTO(pagamento);
    }

    public PagamentoDTO createPagamento(PagamentoDTO dto) {
        Pagamento pagamento = pagamentoMapper.toEntity(dto);
        pagamento.setStatus(Status.CRIADO);

        Pagamento pagamentoSalvo = pagamentosRepository.save(pagamento);
        return pagamentoMapper.toDTO(pagamentoSalvo);
    }

    public PagamentoDTO updatePagamento(Long id, PagamentoDTO dto) {

        Pagamento pagamento = pagamentosRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        pagamentoMapper.updateEntity(dto, pagamento);

        Pagamento pagamentoAtualizado = pagamentosRepository.save(pagamento);
        return pagamentoMapper.toDTO(pagamentoAtualizado);
    }

    public  void deleteById(Long id) {

        Pagamento pagamento = pagamentosRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        pagamentosRepository.deleteById(id);
    }

}
