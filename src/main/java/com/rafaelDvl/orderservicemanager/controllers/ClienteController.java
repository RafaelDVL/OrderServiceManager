package com.rafaelDvl.orderservicemanager.controllers;

import com.rafaelDvl.orderservicemanager.DTOS.ClienteDTO;
import com.rafaelDvl.orderservicemanager.DTOS.TecnicoDTO;
import com.rafaelDvl.orderservicemanager.domain.Cliente;
import com.rafaelDvl.orderservicemanager.domain.Tecnico;
import com.rafaelDvl.orderservicemanager.services.ClienteService;
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
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;


    @GetMapping
    public ResponseEntity<List<ClienteDTO>> findAll(){
        List<Cliente> lista = service.findAll();
        List<ClienteDTO> listDTO = new ArrayList<>();
        for (Cliente cliente : lista){
            listDTO.add(new ClienteDTO(cliente));
        }
        return ResponseEntity.status(HttpStatus.OK).body(listDTO);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> findById(@PathVariable Integer id){
        ClienteDTO objDTO = new ClienteDTO(service.findById(id));
        return ResponseEntity.ok().body(objDTO);
    }


    @PostMapping
    public ResponseEntity<ClienteDTO> create(@RequestBody @Valid ClienteDTO objDTO){
        Cliente newObj = service.create(objDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> update(@PathVariable Integer id,@Valid @RequestBody ClienteDTO objDTO){
        ClienteDTO newObj = new ClienteDTO(service.update(id, objDTO));
        return ResponseEntity.status(HttpStatus.OK).body(newObj);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
