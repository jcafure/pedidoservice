package br.com.desafio.senior.api.pedido.service.serviceimp;

import br.com.desafio.senior.api.pedido.domain.SituacaoPedido;
import br.com.desafio.senior.api.pedido.domain.Tipo;
import br.com.desafio.senior.api.pedido.entity.ItemPedido;
import br.com.desafio.senior.api.pedido.entity.Pedido;
import br.com.desafio.senior.api.pedido.exception.ObjectNotFoundException;
import br.com.desafio.senior.api.pedido.repository.PedidoRepository;
import br.com.desafio.senior.api.pedido.service.IPedidoService;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class PedidoServiceImp implements IPedidoService {

    private final PedidoRepository pedidoRepository;

    @Autowired
    public PedidoServiceImp(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public Pedido calcularDescontoProduto(Pedido pedido) {
        pedido.setDataPedido(new Date());
        if (SituacaoPedido.ABERTA.equals(pedido.getSituacaoPedido())) {
            pedido.getItensPedidos().forEach(itemPedido -> {
                if (Tipo.PRODUTO.equals(itemPedido.getProdutoServico().getTipo())) {
                    BigDecimal total = BigDecimal.ZERO;
                    BigDecimal totalPedido = getTotalPedido(itemPedido);
                    if (pedido.getDesconto().compareTo(BigDecimal.ZERO) > 0) {
                        BigDecimal desconto = calcularDesconto(totalPedido, pedido.getDesconto());
                        BigDecimal valorComDesconto = subtrairTotalPedidoComDesconto(totalPedido, desconto);
                        total = total.add(valorComDesconto);
                    }

                    pedido.setTotalSemDesconto(totalPedido);
                    pedido.setTotalComDesconto(total);
                }
                else {
                    pedido.setTotalSemDesconto(getTotalPedido(itemPedido));
                }
            });
        }

        return pedido;
    }


    @Override
    public Optional<Pedido> save(Pedido pedido) {
        pedido = calcularDescontoProduto(pedido);
        return Optional.of(pedidoRepository.save(pedido));
    }

    @Override
    public Optional<Pedido> update(Pedido pedido) {
        Optional<Pedido> pedidoUpdate = findById(pedido.getId());
         pedidoUpdate = atualizarPedido(pedidoUpdate, pedido);
        return Optional.of(pedidoRepository.save(pedidoUpdate.get()));
    }

    private Optional<Pedido> atualizarPedido(Optional<Pedido> pedidoUpdate, Pedido pedido) {

        if (!pedido.getItensPedidos().isEmpty()) {
            pedidoUpdate.get().setItensPedidos(pedido.getItensPedidos());
        }
        pedidoUpdate.get().setSituacaoPedido(pedido.getSituacaoPedido());
        if (pedido.getDesconto() != null && SituacaoPedido.ABERTA.equals(pedido.getSituacaoPedido())){
            pedidoUpdate.get().setDesconto(pedido.getDesconto());
            calcularDescontoProduto(pedidoUpdate.get());
        }
        pedidoUpdate.get().setDataAtualizacao(new Date());

        return pedidoUpdate;


    }

    @Override
    public Optional<Pedido> findById(UUID id) {
        Optional<Pedido> produto = pedidoRepository.findById(id);
        return Optional.ofNullable(produto.orElseThrow(() ->
                new ObjectNotFoundException("Produto não encontrado.")));
    }

    @Override
    public Iterable<Pedido> findAll(Predicate predicate) {
        return pedidoRepository.findAll(predicate);
    }

    @Override
    public void delete(UUID id) {
        Optional<Pedido> pedido = findById(id);
        pedidoRepository.delete(pedido.get());

    }

    private BigDecimal getTotalPedido(ItemPedido itemPedido) {
        return itemPedido.getProdutoServico().getValor().multiply(new BigDecimal(itemPedido.getQuantidade()));
    }

    private BigDecimal calcularDesconto(BigDecimal totalPedido, BigDecimal desconto) {
        return totalPedido.multiply(desconto.divide(new BigDecimal(100)).setScale(2, RoundingMode.HALF_EVEN));
    }

    private BigDecimal subtrairTotalPedidoComDesconto(BigDecimal totalPedido, BigDecimal desconto) {
        return totalPedido.subtract(desconto);
    }
}
