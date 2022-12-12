package com.rafaelDvl.orderservicemanager.controllers;

import com.rafaelDvl.orderservicemanager.DTOS.ClienteDTO;
import com.rafaelDvl.orderservicemanager.DTOS.OSDTO;
import com.rafaelDvl.orderservicemanager.domain.OS;
import com.rafaelDvl.orderservicemanager.repositories.OsRepository;
import com.rafaelDvl.orderservicemanager.services.OsServices;
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
@RequestMapping("/os")
public class OSController {

    @Autowired
    public OsServices osServices;
    @Autowired
    private OsRepository osRepository;


    @GetMapping
    public ResponseEntity<List<OSDTO>> findAll(){
        List<OS> listOs = osServices.findAll();
        List<OSDTO> listDTO = new ArrayList<>();
        for (OS os : listOs) {
            listDTO.add(new OSDTO(os));
        }
        return ResponseEntity.status(HttpStatus.OK).body(listDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OSDTO> findById(@PathVariable Integer id){
        OSDTO obj = new OSDTO(osServices.findById(id));
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<OSDTO> create(@RequestBody @Valid OSDTO osdto){
        OS newos = osServices.create(osdto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newos.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<OSDTO> update(@PathVariable Integer id, @RequestBody @Valid OSDTO osdto){
        OSDTO newObj = new OSDTO(osServices.update(osdto));
        return ResponseEntity.status(HttpStatus.OK).body(newObj);
    }
}
