package com.rafaelDvl.orderservicemanager.controllers;


import com.rafaelDvl.orderservicemanager.DTOS.TecnicoDTO;
import com.rafaelDvl.orderservicemanager.domain.Tecnico;
import com.rafaelDvl.orderservicemanager.services.TecnicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tecnicos")
public class TecnicoController {

    @Autowired
    private TecnicoService service;


    @GetMapping
    public ResponseEntity<List<TecnicoDTO>> findAll(){
        List<Tecnico> lista = service.findAll();
        List<TecnicoDTO> listDTO = new ArrayList<>();
        for (Tecnico tecnico : lista){
            listDTO.add(new TecnicoDTO(tecnico));
        }
        return ResponseEntity.status(HttpStatus.OK).body(listDTO);
    }


    @GetMapping("/{id}")
    public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id){
        TecnicoDTO objDTO = new TecnicoDTO(service.findById(id));
        return ResponseEntity.ok().body(objDTO);
    }


    @PostMapping
    public ResponseEntity<TecnicoDTO> create(@RequestBody @Valid TecnicoDTO objDTO){
        Tecnico newObj = service.create(objDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TecnicoDTO> update(@PathVariable Integer id,@Valid @RequestBody TecnicoDTO objDTO){
        TecnicoDTO newObj = new TecnicoDTO(service.update(id, objDTO));
        return ResponseEntity.status(HttpStatus.OK).body(newObj);
    }

    @DeleteMapping("/{id}")
        public ResponseEntity<Void> delete(@PathVariable Integer id){
            service.delete(id);
            return ResponseEntity.noContent().build();
        }


}
