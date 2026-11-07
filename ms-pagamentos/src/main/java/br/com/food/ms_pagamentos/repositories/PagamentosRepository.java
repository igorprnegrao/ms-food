package br.com.food.ms_pagamentos.repositories;

import br.com.food.ms_pagamentos.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentosRepository extends JpaRepository<Pagamento, Long> {

}
