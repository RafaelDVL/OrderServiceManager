package com.rafaelDvl.orderservicemanager.services;


import com.rafaelDvl.orderservicemanager.DTOS.TecnicoDTO;
import com.rafaelDvl.orderservicemanager.domain.Tecnico;
import com.rafaelDvl.orderservicemanager.repositories.TecnicoRepository;
import com.rafaelDvl.orderservicemanager.services.exceptions.DataIntegratyViolationException;
import com.rafaelDvl.orderservicemanager.services.exceptions.ObjectNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    TecnicoRepository tecnicoRepository;

    public Tecnico findById(Integer id) {
        Optional<Tecnico> obj = tecnicoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo " + Tecnico.class.getName()));
    }

    public List<Tecnico> findAll() {

        return tecnicoRepository.findAll();
    }


    public Tecnico create(TecnicoDTO objDTO) {
        if(findByCPF(objDTO) != null){
            throw new DataIntegratyViolationException("CPF ja cadastrado na base de dados!!!");
        }

        return tecnicoRepository.save(new Tecnico(null, objDTO.getNome(), objDTO.getCpf(), objDTO.getTelefone()));
    }


    public Tecnico findByCPF(TecnicoDTO objDTO){
        Tecnico obj = tecnicoRepository.findByCPF(objDTO.getCpf());
        if(obj != null){
            return obj;
        }
        return null;
    }

    public Tecnico update(Integer id, @Valid TecnicoDTO objDTO) {
        Tecnico oldObj = findById(id);
        if(findByCPF(objDTO) != null && findByCPF(objDTO).getId() != id){
            throw new DataIntegratyViolationException("CPF ja cadastrado no sistema!!!");
        }
        oldObj.setNome(objDTO.getNome());
        oldObj.setCpf(objDTO.getCpf());
        oldObj.setTelefone(objDTO.getTelefone());

        return tecnicoRepository.save(oldObj);
    }

    public void delete(Integer id) {
        tecnicoRepository.deleteById(id);
    }
}
