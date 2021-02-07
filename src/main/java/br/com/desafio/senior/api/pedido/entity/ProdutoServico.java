package br.com.desafio.senior.api.pedido.entity;

import br.com.desafio.senior.api.pedido.domain.Tipo;
import com.querydsl.core.annotations.QueryEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@QueryEntity
public class ProdutoServico extends BaseEntity {

    private String nome;

    private String descricao;

    private BigDecimal valor;

    @Enumerated(EnumType.ORDINAL)
    private Tipo tipo;

    private boolean ativo;
}
