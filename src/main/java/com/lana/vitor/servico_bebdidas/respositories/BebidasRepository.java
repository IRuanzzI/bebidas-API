package com.lana.vitor.servico_bebdidas.respositories;

import com.lana.vitor.servico_bebdidas.entities.Bebidas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BebidasRepository extends JpaRepository<Bebidas, Long> {
}
