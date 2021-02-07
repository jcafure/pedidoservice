package br.com.desafio.senior.api.pedido.controller;

import br.com.desafio.senior.api.pedido.entity.ItemPedido;
import br.com.desafio.senior.api.pedido.service.serviceimp.ItemPedidoServiceImp;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/itens")
public class ItemPedidoController  {

    private final ItemPedidoServiceImp itemPedidoServiceImp;

    @Autowired
    public ItemPedidoController(ItemPedidoServiceImp itemPedidoServiceImp) {
        this.itemPedidoServiceImp = itemPedidoServiceImp;
    }

    @PostMapping(value = "/salvar")
    public ResponseEntity<?> save( @Valid @RequestBody ItemPedido itemPedido) {
        return ResponseEntity.ok(this.itemPedidoServiceImp.save(itemPedido));
    }

    @PutMapping(value = "/atualizar")
    public ResponseEntity<?> update(@RequestBody ItemPedido itemPedido) {
        Optional<ItemPedido> item = itemPedidoServiceImp.update(itemPedido);
        return ResponseEntity.ok(item.get());
    }

    @GetMapping(value = "/listar-itens")
    public Iterable<ItemPedido> findAll(@QuerydslPredicate(root = ItemPedido.class) Predicate predicate) {
        return itemPedidoServiceImp.findAll(predicate);
    }

    @PutMapping(value = "/deletar-item/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        itemPedidoServiceImp.delete(id);
        return ResponseEntity.ok().build();
    }
}
