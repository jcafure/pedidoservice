package br.com.desafio.senior.api.pedido.service.serviceimp;

import br.com.desafio.senior.api.pedido.entity.ItemPedido;
import br.com.desafio.senior.api.pedido.entity.ProdutoServico;
import br.com.desafio.senior.api.pedido.exception.ObjectNotFoundException;
import br.com.desafio.senior.api.pedido.repository.ItemPedidoRepository;
import br.com.desafio.senior.api.pedido.service.IBaseService;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ItemPedidoServiceImp implements IBaseService<ItemPedido> {

    private final ItemPedidoRepository itemPedidoRepository;

    @Autowired
    public ItemPedidoServiceImp(ItemPedidoRepository itemPedidoRepository) {
        this.itemPedidoRepository = itemPedidoRepository;
    }

    @Override
    public Optional<ItemPedido> save(ItemPedido itemPedido) {
        validarProduto(itemPedido.getProdutoServico());
        return Optional.of(itemPedidoRepository.save(itemPedido));
    }

    @Override
    public Optional<ItemPedido> update(ItemPedido itemPedido) {
        Optional<ItemPedido> optionalItemPedido = findById(itemPedido.getId());
        optionalItemPedido.get().setQuantidade(itemPedido.getQuantidade());
        validarProduto(itemPedido.getProdutoServico());
        optionalItemPedido.get().setProdutoServico(itemPedido.getProdutoServico());
        return Optional.of(itemPedidoRepository.save(optionalItemPedido.get()));
    }

    @Override
    public Optional<ItemPedido> findById(UUID id) {
        Optional<ItemPedido> produto = itemPedidoRepository.findById(id);
        return Optional.ofNullable(produto.orElseThrow(() ->
                new ObjectNotFoundException("Produto não encontrado.")));
    }

    @Override
    public Iterable<ItemPedido> findAll(Predicate predicate) {
        return itemPedidoRepository.findAll(predicate);
    }

    @Override
    public void delete(UUID id) {
        Optional<ItemPedido> itemPedido = findById(id);
        itemPedidoRepository.delete(itemPedido.get());

    }

    private void validarProduto(ProdutoServico produtoServico) {
        if (!produtoServico.isAtivo()) {
            throw new RuntimeException("Não é possível adicionar um produto inativo.");
        }
    }
}
