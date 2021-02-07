package br.com.desafio.senior.api.pedido.controller;

import br.com.desafio.senior.api.pedido.entity.Pedido;
import br.com.desafio.senior.api.pedido.service.serviceimp.PedidoServiceImp;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoServiceImp pedidoServiceImp;

    @Autowired
    public PedidoController(PedidoServiceImp pedidoServiceImp) {
        this.pedidoServiceImp = pedidoServiceImp;
    }

    @PostMapping(value = "/salvar")
    public ResponseEntity<?> save(@RequestBody Pedido pedido) {
        return ResponseEntity.ok(this.pedidoServiceImp.save(pedido));
    }

    @PutMapping(value = "/atualizar-pedido")
    public ResponseEntity<?> update(@RequestBody Pedido pedido) {
        return ResponseEntity.ok(this.pedidoServiceImp.update(pedido));
    }

    @DeleteMapping(value = "/deletar-pedido/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id) {
        pedidoServiceImp.delete(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping(value = "/listar-pedidos")
    public Iterable<Pedido> findAll(@QuerydslPredicate(root = Pedido.class) Predicate predicate) {
        return pedidoServiceImp.findAll(predicate);
    }
}
