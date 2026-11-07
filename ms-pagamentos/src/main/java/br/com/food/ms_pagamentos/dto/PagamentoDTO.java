package br.com.food.ms_pagamentos.dto;

import br.com.food.ms_pagamentos.model.Status;

import java.math.BigDecimal;

public record PagamentoDTO(
        Long id,
        BigDecimal valor,
        String nome,
        String numero,
        String expiracao,
        String codigo,
        Status status,
        Long pedidoId,
        Long formaDePagamentoId
) {
}
