package br.com.desafio.senior.api.pedido.service;

import br.com.desafio.senior.api.pedido.entity.ItemPedido;
import br.com.desafio.senior.api.pedido.entity.Pedido;
import br.com.desafio.senior.api.pedido.entity.ProdutoServico;
import br.com.desafio.senior.api.pedido.repository.PedidoRepository;
import br.com.desafio.senior.api.pedido.service.serviceimp.PedidoServiceImp;
import br.com.desafio.senior.api.pedido.util.MockUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
public class PedidoServiceTest {

    @InjectMocks
    private PedidoServiceImp pedidoServiceImp;

    @Mock
    private PedidoRepository pedidoRepository;

    private List<ItemPedido> itensPedidos = new ArrayList<>();
    private Pedido pedido;
    private ProdutoServico produtoServico;

    @Before
    public void initialize() {
        produtoServico = MockUtils.buildProduto();
        itensPedidos = Arrays.asList(MockUtils.buildItemPedido(produtoServico));
        pedido = MockUtils.buildPedido(produtoServico);
    }

    @Test
    public void calcularDescontoProduto() {
        pedido = pedidoServiceImp.calcularDescontoProduto(pedido);
        Double valorTotaComDesconto = Double.valueOf(pedido.getTotalComDesconto().doubleValue());
        assertTrue(valorTotaComDesconto != null);

    }

    @Test
    public void findByUUIDTest() {
        when(this.pedidoRepository.findById(pedido.getId())).thenReturn(Optional.of(pedido));
        Optional<Pedido> pedidoOptional = this.pedidoServiceImp.findById(pedido.getId());
        assertTrue(pedidoOptional.isPresent());
    }
}
