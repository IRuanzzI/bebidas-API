package com.lana.vitor.servico_bebdidas.service;

import com.lana.vitor.servico_bebdidas.dto.BebidasDTO;
import com.lana.vitor.servico_bebdidas.dto.BebidasResponseDTO;
import com.lana.vitor.servico_bebdidas.dto.SellDTO;
import com.lana.vitor.servico_bebdidas.entities.Bebidas;
import com.lana.vitor.servico_bebdidas.respositories.BebidasRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BebidasService {

    private final BebidasRepository bebidasRepository;

    public BebidasResponseDTO create(BebidasDTO dto) {
        var entity = new Bebidas();
        entity.setName(dto.getName().toUpperCase().replace(" ", "-"));
        entity.setQuantity(dto.getQuantity());
        entity.setStatus(dto.getQuantity() > 0);

        var savedEntity = bebidasRepository.save(entity);
        return new BebidasResponseDTO(savedEntity.getId(), savedEntity.getName(), savedEntity.getQuantity());
    }

    public List<Bebidas> findAll() {
        return bebidasRepository.findAll();
    }

    public void sell(SellDTO dto) {
        var entity = bebidasRepository.findById(dto.getId()).orElseThrow();
        if (entity.getQuantity() < dto.getQuantity()) {
            throw new RuntimeException("Quantidade insuficiente");
        }

        int res = entity.getQuantity() - dto.getQuantity();
        entity.setQuantity(res);

        if (entity.getQuantity() <= 0) {
            entity.setStatus(false);
        }

        bebidasRepository.save(entity);
    }

    public void addQuantity(SellDTO dto) {
        var entity = bebidasRepository.findById(dto.getId()).orElseThrow();
        int res = entity.getQuantity() + dto.getQuantity();
        entity.setQuantity(res);

        if (entity.getQuantity() > 0) {
            entity.setStatus(true);
        }

        bebidasRepository.save(entity);
    }

    public void delete(Long id) {
        if (!bebidasRepository.existsById(id)) {
            throw new RuntimeException("Bebida não encontrada");
        }
        bebidasRepository.deleteById(id);
    }
}
