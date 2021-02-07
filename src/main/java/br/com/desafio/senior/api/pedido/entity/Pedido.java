package br.com.desafio.senior.api.pedido.entity;

import br.com.desafio.senior.api.pedido.domain.SituacaoPedido;
import com.querydsl.core.annotations.QueryEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@QueryEntity
public class Pedido extends BaseEntity {

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataPedido;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAtualizacao;

    @Enumerated(EnumType.ORDINAL)
    private SituacaoPedido situacaoPedido;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ItemPedido> itensPedidos = new ArrayList<>();

    private BigDecimal desconto;

    private BigDecimal totalSemDesconto;

    private BigDecimal totalComDesconto;





}
