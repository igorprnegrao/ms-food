package br.com.food.ms_pagamentos.services;

import br.com.food.ms_pagamentos.dto.PagamentoDTO;
import br.com.food.ms_pagamentos.mapper.PagamentoMapper;
import br.com.food.ms_pagamentos.model.Pagamento;
import br.com.food.ms_pagamentos.model.Status;
import br.com.food.ms_pagamentos.repositories.PagamentosRepository;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PagamentoServiceTest {

    @Test
    void deveCriarPagamentoComNovoIdEStatusCriado() {
        PagamentosRepository repository = mock(PagamentosRepository.class);
        PagamentoService service = new PagamentoService(
                repository, Mappers.getMapper(PagamentoMapper.class));
        PagamentoDTO dto = new PagamentoDTO(
                99L, new BigDecimal("25.50"), "Cliente", "4111111111111111",
                "12/2030", "123", Status.CONFIRMADO, 2L, 3L);

        when(repository.save(any(Pagamento.class))).thenAnswer(invocation -> {
            Pagamento pagamento = invocation.getArgument(0);
            assertNull(pagamento.getId());
            assertEquals(Status.CRIADO, pagamento.getStatus());
            pagamento.setId(1L);
            return pagamento;
        });

        assertEquals(new PagamentoDTO(
                1L, dto.valor(), dto.nome(), dto.numero(), dto.expiracao(),
                dto.codigo(), Status.CRIADO, dto.pedidoId(), dto.formaDePagamentoId()),
                service.createPagamento(dto));
        verify(repository).save(any(Pagamento.class));
    }
}
