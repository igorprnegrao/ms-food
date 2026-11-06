package br.com.food.ms_pagamentos.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "pagamentos")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Pagamento implements Serializable {


    @Serial
    private  static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull @Positive
    private BigDecimal valor;

    @NotBlank @Size(max = 100)
    private String nome;

    @NotBlank @Size(max = 19)
    private String numero;

    @NotBlank @Size(max = 7)
    private String expiracao;

    @NotBlank @Size(min= 3, max = 3)
    private String codigo;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "pedido_id")
    private Long pedidoId;

    @Column(name = "forma_pagamento_id")
    private Long formaDePagamentoId;

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Pagamento pagamento)) return false;

        return getId().equals(pagamento.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
