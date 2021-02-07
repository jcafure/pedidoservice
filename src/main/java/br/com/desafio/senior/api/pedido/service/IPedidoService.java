package br.com.desafio.senior.api.pedido.service;

import br.com.desafio.senior.api.pedido.entity.Pedido;


public interface IPedidoService extends IBaseService<Pedido> {

    Pedido calcularDescontoProduto(Pedido pedido);
}
