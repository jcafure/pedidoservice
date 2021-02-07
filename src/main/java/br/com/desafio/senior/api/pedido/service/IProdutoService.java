package br.com.desafio.senior.api.pedido.service;

import br.com.desafio.senior.api.pedido.dto.ProdutoDTO;
import br.com.desafio.senior.api.pedido.entity.ProdutoServico;

import java.util.Optional;

public interface IProdutoService extends IBaseService<ProdutoServico>{

    Optional<ProdutoServico> save (ProdutoDTO produtoDTO);
    Optional<ProdutoServico> update (ProdutoDTO produtoDTO);

}
