package com.rafaelDvl.orderservicemanager.DTOS;

import com.rafaelDvl.orderservicemanager.domain.Tecnico;
import jakarta.validation.constraints.NotEmpty;

import java.io.Serializable;

public class TecnicoDTO implements Serializable {


    private static final long serialVersionUID = 1L;
    private Integer id;
    @NotEmpty(message = "O campo nome é requirido!")
    private String nome;
    @NotEmpty(message = "O campo CPF é obrigatorio!")
    private String cpf;
    @NotEmpty(message = "O campo telefone é requirido")
    private String telefone;

    public TecnicoDTO() {
        super();
    }

    public TecnicoDTO(Tecnico obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.cpf = obj.getCpf();
        this.telefone = obj.getTelefone();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
