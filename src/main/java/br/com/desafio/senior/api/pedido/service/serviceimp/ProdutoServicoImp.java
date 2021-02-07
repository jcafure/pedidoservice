package br.com.desafio.senior.api.pedido.service.serviceimp;

import br.com.desafio.senior.api.pedido.dto.ProdutoDTO;
import br.com.desafio.senior.api.pedido.entity.ProdutoServico;
import br.com.desafio.senior.api.pedido.exception.ObjectNotFoundException;
import br.com.desafio.senior.api.pedido.repository.ProdutoServicoRepository;
import br.com.desafio.senior.api.pedido.service.IProdutoService;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ProdutoServicoImp implements IProdutoService {

    private final ProdutoServicoRepository produtoServicoRepository;

    @Autowired
    public ProdutoServicoImp(ProdutoServicoRepository produtoServicoRepository) {
        this.produtoServicoRepository = produtoServicoRepository;
    }

    @Override
    public Optional<ProdutoServico> save(ProdutoDTO produtoDTO) {
        ProdutoServico produtoServico = buildProdutoServico(produtoDTO);
        return Optional.ofNullable(produtoServicoRepository.save(produtoServico));
    }

    @Override
    public Optional<ProdutoServico> update(ProdutoDTO produtoDTO) {
        Optional<ProdutoServico> produtoAtual = produtoServicoRepository.findById(produtoDTO.getId());
        produtoAtual.get().setDescricao(produtoDTO.getDescricao());
        produtoAtual.get().setNome(produtoDTO.getNome());
        produtoAtual.get().setTipo(produtoDTO.getTipo());
        produtoAtual.get().setAtivo(produtoDTO.isAtivo());

        return Optional.ofNullable(produtoServicoRepository.save(produtoAtual.get()));
    }

    @Override
    public Optional<ProdutoServico> findById(UUID id) {
        Optional<ProdutoServico> produto = produtoServicoRepository.findById(id);
        return Optional.ofNullable(produto.orElseThrow(() ->
                new ObjectNotFoundException("Produto não encontrado.")));
    }

    @Override
    public Iterable<ProdutoServico> findAll(Predicate predicate) {
        return produtoServicoRepository.findAll(predicate);
    }

    @Override
    public void delete(UUID id) {
        Optional<ProdutoServico> produtoServico = produtoServicoRepository.findById(id);
        produtoServicoRepository.delete(produtoServico.get());
    }

    private ProdutoServico buildProdutoServico(ProdutoDTO produtoDTO) {
        return ProdutoServico.builder().nome(produtoDTO.getNome())
                .descricao(produtoDTO.getDescricao())
                .valor(produtoDTO.getValor())
                .ativo(produtoDTO.isAtivo())
                .tipo(produtoDTO.getTipo())
                .build();
    }


    @Override
    public Optional<ProdutoServico> save(ProdutoServico produtoServico) {
        return Optional.empty();
    }

    @Override
    public Optional<ProdutoServico> update(ProdutoServico produtoServico) {
        return Optional.empty();
    }


}
