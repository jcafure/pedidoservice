package br.com.desafio.senior.api.pedido.service;

import com.querydsl.core.types.Predicate;

import java.util.Optional;
import java.util.UUID;

public interface IBaseService <T>{

    Optional<T> save(T t);
    Optional<T> update(T t);
    Optional<T> findById(UUID id);
    Iterable<T> findAll(Predicate predicate);
    void delete(UUID id);
}
