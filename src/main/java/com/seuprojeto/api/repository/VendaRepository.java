package com.seuprojeto.api.repository;

import com.seuprojeto.api.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendaRepository extends JpaRepository<Venda, Long> {
}
