package com.seuprojeto.api.service;

import com.seuprojeto.api.dto.ProdutoDTO;
import com.seuprojeto.api.exception.NotFoundException;
import com.seuprojeto.api.model.Produto;
import com.seuprojeto.api.repository.ProdutoRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public Produto criar(ProdutoDTO dto) {
        return repository.save(new Produto(dto.nome, dto.preco, dto.estoque));
    }

    public List<Produto> listar() {
        return repository.findAll();
    }

    public Produto buscar(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Produto nao encontrado"));
    }

    public Produto atualizar(Long id, ProdutoDTO dto) {
        Produto produto = buscar(id);
        produto.setNome(dto.nome);
        produto.setPreco(dto.preco);
        produto.setEstoque(dto.estoque);
        return repository.save(produto);
    }

    public void deletar(Long id) {
        repository.delete(buscar(id));
    }
}
