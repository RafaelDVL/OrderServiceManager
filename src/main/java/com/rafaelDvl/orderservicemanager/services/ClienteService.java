package com.rafaelDvl.orderservicemanager.services;

import com.rafaelDvl.orderservicemanager.DTOS.ClienteDTO;
import com.rafaelDvl.orderservicemanager.DTOS.TecnicoDTO;
import com.rafaelDvl.orderservicemanager.domain.Cliente;
import com.rafaelDvl.orderservicemanager.domain.Pessoa;
import com.rafaelDvl.orderservicemanager.domain.Tecnico;
import com.rafaelDvl.orderservicemanager.repositories.ClienteRepository;
import com.rafaelDvl.orderservicemanager.repositories.PessoaRepository;
import com.rafaelDvl.orderservicemanager.repositories.TecnicoRepository;
import com.rafaelDvl.orderservicemanager.services.exceptions.DataIntegratyViolationException;
import com.rafaelDvl.orderservicemanager.services.exceptions.ObjectNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    PessoaRepository pessoaRepository;

    public Cliente findById(Integer id) {
        Optional<Cliente> obj = clienteRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo " + Cliente.class.getName()));
    }

    public List<Cliente> findAll() {

        return clienteRepository.findAll();
    }


    public Cliente create(ClienteDTO objDTO) {
        if(findByCPF(objDTO) != null){
            throw new DataIntegratyViolationException("CPF ja cadastrado na base de dados!!!");
        }
        return clienteRepository.save(new Cliente(null, objDTO.getNome(), objDTO.getCpf(), objDTO.getTelefone()));
    }


    public Pessoa findByCPF(ClienteDTO objDTO){
        Pessoa obj = pessoaRepository.findByCPF(objDTO.getCpf());
        if(obj != null){
            return obj;
        }
        return null;
    }

    public Cliente update(Integer id, @Valid ClienteDTO objDTO) {
        Cliente oldObj = findById(id);
        if(findByCPF(objDTO) != null && findByCPF(objDTO).getId() != id){
            throw new DataIntegratyViolationException("CPF ja cadastrado no sistema!!!");
        }
        oldObj.setNome(objDTO.getNome());
        oldObj.setCpf(objDTO.getCpf());
        oldObj.setTelefone(objDTO.getTelefone());

        return clienteRepository.save(oldObj);
    }

    public void delete(Integer id) {
        Cliente obj = findById(id);
        if(obj.getList().size() > 0){
            throw new DataIntegratyViolationException("Cliente possui ordens de serviço, não pode ser deletado!!!");
        }
        clienteRepository.deleteById(id);
    }
}
