package br.com.food.ms_pagamentos.mapper;

import br.com.food.ms_pagamentos.dto.PagamentoDTO;
import br.com.food.ms_pagamentos.model.Pagamento;
import br.com.food.ms_pagamentos.model.Status;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PagamentoMapperTest {

    private final PagamentoMapper mapper = Mappers.getMapper(PagamentoMapper.class);

    @Test
    void deveConverterEntidadeLombokParaRecordPreservandoTodosOsCampos() {
        Pagamento pagamento = new Pagamento(
                1L, new BigDecimal("25.50"), "Cliente", "4111111111111111",
                "12/2030", "123", Status.CRIADO, 2L, 3L
        );

        PagamentoDTO esperado = new PagamentoDTO(
                1L, new BigDecimal("25.50"), "Cliente", "4111111111111111",
                "12/2030", "123", Status.CRIADO, 2L, 3L
        );

        assertEquals(esperado, mapper.toDTO(pagamento));
    }
}
