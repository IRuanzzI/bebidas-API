package com.lana.vitor.servico_bebdidas.controllers;

import ch.qos.logback.core.status.Status;
import com.lana.vitor.servico_bebdidas.dto.BebidasDTO;
import com.lana.vitor.servico_bebdidas.dto.BebidasResponseDTO;
import com.lana.vitor.servico_bebdidas.dto.SellDTO;
import com.lana.vitor.servico_bebdidas.service.BebidasService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/bebidas")
public class BebidasController {

    private final BebidasService service;

    @PostMapping("/create")
    ResponseEntity<BebidasResponseDTO> create (@RequestBody BebidasDTO dto){
        var response = service.create(dto);
        var URI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(URI).body(response);
    }

    @GetMapping("/findAll")
    ResponseEntity<?> findAll(){
        var response = service.findAll();
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/sell")
    ResponseEntity<Status> sell(@RequestBody SellDTO dto){
        service.sell(dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/addQuantity")
    ResponseEntity<Status> addQuantity(@RequestBody SellDTO dto){
        service.addQuantity(dto);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            service.delete(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}
