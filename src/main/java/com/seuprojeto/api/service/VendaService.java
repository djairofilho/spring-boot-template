package com.seuprojeto.api.service;

import com.seuprojeto.api.dto.VendaDTO;
import com.seuprojeto.api.exception.NotFoundException;
import com.seuprojeto.api.model.Cliente;
import com.seuprojeto.api.model.Produto;
import com.seuprojeto.api.model.Venda;
import com.seuprojeto.api.repository.ClienteRepository;
import com.seuprojeto.api.repository.ProdutoRepository;
import com.seuprojeto.api.repository.VendaRepository;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class VendaService {

    private final VendaRepository vendaRepository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;

    public VendaService(VendaRepository vendaRepository, ClienteRepository clienteRepository, ProdutoRepository produtoRepository) {
        this.vendaRepository = vendaRepository;
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
    }

    public Venda criar(VendaDTO dto) {
        Cliente cliente = clienteRepository.findById(dto.clienteId)
                .orElseThrow(() -> new NotFoundException("Cliente nao encontrado"));

        Produto produto = produtoRepository.findById(dto.produtoId)
                .orElseThrow(() -> new NotFoundException("Produto nao encontrado"));

        produto.reduzirEstoque(dto.quantidade);

        Venda venda = new Venda(
                cliente,
                produto,
                dto.quantidade,
                LocalDate.parse(dto.data)
        );

        produtoRepository.save(produto);
        return vendaRepository.save(venda);
    }

    public List<Venda> listar() {
        return vendaRepository.findAll();
    }

    public Venda buscar(Long id) {
        return vendaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Venda nao encontrada"));
    }

    public void deletar(Long id) {
        vendaRepository.delete(buscar(id));
    }
}
