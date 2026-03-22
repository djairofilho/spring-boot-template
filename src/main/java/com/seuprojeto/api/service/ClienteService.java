package com.seuprojeto.api.service;

import com.seuprojeto.api.dto.ClienteDTO;
import com.seuprojeto.api.exception.NotFoundException;
import com.seuprojeto.api.model.Cliente;
import com.seuprojeto.api.repository.ClienteRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public Cliente criar(ClienteDTO dto) {
        return repository.save(new Cliente(dto.nome, dto.email));
    }

    public List<Cliente> listar() {
        return repository.findAll();
    }

    public Cliente buscar(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cliente nao encontrado"));
    }

    public Cliente atualizar(Long id, ClienteDTO dto) {
        Cliente cliente = buscar(id);
        cliente.setNome(dto.nome);
        cliente.setEmail(dto.email);
        return repository.save(cliente);
    }

    public void deletar(Long id) {
        repository.delete(buscar(id));
    }
}
