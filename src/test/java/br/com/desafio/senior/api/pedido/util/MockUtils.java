package br.com.desafio.senior.api.pedido.util;

import br.com.desafio.senior.api.pedido.domain.SituacaoPedido;
import br.com.desafio.senior.api.pedido.domain.Tipo;
import br.com.desafio.senior.api.pedido.entity.ItemPedido;
import br.com.desafio.senior.api.pedido.entity.Pedido;
import br.com.desafio.senior.api.pedido.entity.ProdutoServico;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

public class MockUtils {

    public static ProdutoServico buildProduto () {
        ProdutoServico produtoServico = ProdutoServico.builder()
                .nome("Sofa")
                .descricao("Objeto Sala")
                .valor(new BigDecimal(1000))
                .ativo(true)
                .tipo(Tipo.PRODUTO)
                .build();
        produtoServico.setId(UUID.randomUUID());
        return produtoServico;
    }

    public static ItemPedido buildItemPedido (ProdutoServico produtoServico) {
        ItemPedido itemPedido = ItemPedido.builder()
                .produtoServico(produtoServico)
                .quantidade(2)
                .build();
        itemPedido.setId(UUID.randomUUID());
        return itemPedido;
    }

    public static Pedido buildPedido(ProdutoServico produtoServico) {
        Pedido pedido = Pedido.builder()
                .dataPedido(new Date())
                .desconto(new BigDecimal(2))
                .situacaoPedido(SituacaoPedido.ABERTA)
                .itensPedidos(Arrays.asList(buildItemPedido(produtoServico)))
                .build();

        pedido.setId(UUID.randomUUID());
        return pedido;
    }


}
