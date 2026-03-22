package com.seuprojeto.api.controller;

import com.seuprojeto.api.dto.VendaDTO;
import com.seuprojeto.api.model.Venda;
import com.seuprojeto.api.service.VendaService;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vendas")
public class VendaController {

    private final VendaService service;

    public VendaController(VendaService service) {
        this.service = service;
    }

    @PostMapping
    public Venda criar(@RequestBody VendaDTO dto) {
        return service.criar(dto);
    }

    @GetMapping
    public List<Venda> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Venda buscar(@PathVariable Long id) {
        return service.buscar(id);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}
