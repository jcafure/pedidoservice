package br.com.desafio.senior.api.pedido.controller;

import br.com.desafio.senior.api.pedido.dto.ProdutoDTO;
import br.com.desafio.senior.api.pedido.entity.ProdutoServico;
import br.com.desafio.senior.api.pedido.service.IProdutoService;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/produtos")
public class ProdutoServicoController {

    private final IProdutoService produtoService;

    @Autowired
    public ProdutoServicoController(IProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping(value = "/salvar")
    public ResponseEntity<?> save(@Valid @RequestBody ProdutoDTO produtoDTO) {
        Optional<ProdutoServico> produtoServico = produtoService.save(produtoDTO);
        return ResponseEntity.ok(produtoServico.get());
    }

    @PutMapping(value = "/atualizar")
    public ResponseEntity<?> update(@Valid @RequestBody ProdutoDTO produtoDTO) {
        Optional<ProdutoServico> produtoServico = produtoService.update(produtoDTO);
        return ResponseEntity.ok(produtoServico.get());
    }

    @GetMapping(value = "/listar-produtos")
    public Iterable<ProdutoServico> findAll(@QuerydslPredicate(root = ProdutoServico.class) Predicate predicate) {
        return produtoService.findAll(predicate);
    }

    @DeleteMapping(value = "/deletar-produto/{id}")
    public ResponseEntity<ProdutoServico> deletarUsuario(@Valid @PathVariable("id") UUID id) {
        produtoService.delete(id);
        return ResponseEntity.ok().build();
    }

}


