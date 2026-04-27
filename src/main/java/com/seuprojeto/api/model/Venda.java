package com.seuprojeto.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Cliente cliente;

    @ManyToOne
    private Produto produto;

    private Integer quantidade;
    private LocalDate data;

    public Venda() {
    }

    public Venda(Cliente cliente, Produto produto, Integer quantidade, LocalDate data) {
        this.cliente = cliente;
        this.produto = produto;
        this.quantidade = quantidade;
        this.data = data;
    }
}
