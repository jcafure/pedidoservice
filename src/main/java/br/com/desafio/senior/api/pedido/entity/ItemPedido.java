package br.com.desafio.senior.api.pedido.entity;

import com.querydsl.core.annotations.QueryEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@QueryEntity
public class ItemPedido extends BaseEntity {

    @Min(value = 1, message = "O campo quantidade deve ter no minimo 1 dígito.")
    @NotNull(message = "O campo número de entrada não deve ser nulo.")
    private Integer quantidade;

    @ManyToOne
    @JoinColumn(name = "produtoServico_id")
    private ProdutoServico produtoServico;

}
