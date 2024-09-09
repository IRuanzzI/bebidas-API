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

        entity.setName(dto.getName().toUpperCase());
        entity.setQuantity(dto.getQuantity());

        var res = dto.getQuantity() <= 0 ? false: true;

        entity.setStatus(res);

        var savedEntity = bebidasRepository.save(entity);
        return new BebidasResponseDTO(savedEntity.getId(), savedEntity.getName(), savedEntity.getQuantity());
    }

    public List<Bebidas> findAll() {
        return bebidasRepository.findAll();
    }

    public void sell(SellDTO dto) {
        var entity = bebidasRepository.findById(dto.getId()).orElseThrow();

        if(entity.getQuantity() < dto.getQuantity()){
            throw new RuntimeException("Quantidade insuficiente");
        }

        int res = entity.getQuantity() - dto.getQuantity();
        entity.setQuantity(res);

        // Verifica a condição e altera o status
        if(entity.getQuantity() <= 0){
            entity.setStatus(false);
            System.out.println("Status alterado para false");
        }

        // Salva a entidade com a nova quantidade e status
        bebidasRepository.save(entity);

        // Verificação pós-salvamento (apenas para fins de depuração)
        var updatedEntity = bebidasRepository.findById(dto.getId()).orElseThrow();
        System.out.println("Status atual após salvamento: " + updatedEntity.getStatus());
    }


    public void addQuantity(SellDTO dto) {
        var entity = bebidasRepository.findById(dto.getId()).orElseThrow();

        int res = entity.getQuantity() + dto.getQuantity();

        entity.setQuantity(res);

        if(entity.getQuantity() > 0){
            entity.setStatus(true);
        }
        bebidasRepository.save(entity);
    }


}
