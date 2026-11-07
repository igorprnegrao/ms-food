package br.com.food.ms_pagamentos.mapper;

import br.com.food.ms_pagamentos.dto.PagamentoDTO;
import br.com.food.ms_pagamentos.model.Pagamento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface PagamentoMapper {

    PagamentoDTO toDTO(Pagamento pagamento);

    @Mapping(target = "id", ignore = true)
    Pagamento toEntity(PagamentoDTO dto);

    void updateEntity(PagamentoDTO dto, Pagamento pagamento);
}
