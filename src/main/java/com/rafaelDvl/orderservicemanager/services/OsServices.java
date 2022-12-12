package com.rafaelDvl.orderservicemanager.services;

import com.rafaelDvl.orderservicemanager.DTOS.OSDTO;
import com.rafaelDvl.orderservicemanager.domain.Cliente;
import com.rafaelDvl.orderservicemanager.domain.OS;
import com.rafaelDvl.orderservicemanager.domain.Tecnico;
import com.rafaelDvl.orderservicemanager.domain.enuns.Prioridade;
import com.rafaelDvl.orderservicemanager.domain.enuns.Status;
import com.rafaelDvl.orderservicemanager.repositories.OsRepository;
import com.rafaelDvl.orderservicemanager.services.exceptions.ObjectNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OsServices {

    @Autowired
    private OsRepository osRepository;

    @Autowired
    private TecnicoService tecnicoService;

    @Autowired
    private ClienteService clienteService;

    public OS update(@Valid OSDTO osdto) {
        findById(osdto.getId());
        return fromDTO(osdto);
    }


    public List<OS> findAll(){
        return osRepository.findAll();
    }

    public OS findById(Integer id) {
        Optional<OS> obj = osRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado!" + id + ", tipo: " + OS.class.getName()));
    }

    public OS create(@Valid OSDTO osdto) {
        return fromDTO(osdto);
    }

    public OS fromDTO(OSDTO obj){
        OS newObj = new OS();
        newObj.setId(obj.getId());
        newObj.setObservacoes(obj.getObservacoes());
        newObj.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
        newObj.setStatus(Status.toEnum(obj.getStatus()));
        Tecnico tc = tecnicoService.findById(obj.getTecnico());
        Cliente cl = clienteService.findById(obj.getCliente());

        newObj.setTecnico(tc);
        newObj.setCliente(cl);

        if(newObj.getStatus().getCod().equals(2)){
            newObj.setDataFechamento(LocalDateTime.now());
        }

        return osRepository.save(newObj);
    }
}
