package com.rafaelDvl.orderservicemanager.services;

import com.rafaelDvl.orderservicemanager.domain.Cliente;
import com.rafaelDvl.orderservicemanager.domain.OS;
import com.rafaelDvl.orderservicemanager.domain.Tecnico;
import com.rafaelDvl.orderservicemanager.domain.enuns.Prioridade;
import com.rafaelDvl.orderservicemanager.domain.enuns.Status;
import com.rafaelDvl.orderservicemanager.repositories.ClienteRepository;
import com.rafaelDvl.orderservicemanager.repositories.OsRepository;
import com.rafaelDvl.orderservicemanager.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private TecnicoRepository tecnicoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private OsRepository osRepository;

    public void instaciaDB(){

        Tecnico t1 = new Tecnico(null, "Rafael Rodrigues", "40732834805", "159979078-07");
        Tecnico t2 = new Tecnico(null, "Angelina Jolie", "40732834805", "159979078-07");
        Tecnico t3= new Tecnico(null, "Henrique Mamamiu", "40732834805", "159979078-07");
        Cliente c1 = new Cliente(null, "Joao Jacinto Pinto", "06001624003","19978690544");

        OS os1 = new OS(null, Prioridade.BAIXA, "Trocar lampada no inferno", Status.ABERTO,t1, c1);

        tecnicoRepository.saveAll(Arrays.asList(t1));
        tecnicoRepository.saveAll(Arrays.asList(t2));
        tecnicoRepository.saveAll(Arrays.asList(t3));
        clienteRepository.saveAll(Arrays.asList(c1));
        osRepository.saveAll(Arrays.asList(os1));
    }
}
