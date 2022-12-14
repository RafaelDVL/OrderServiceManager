package com.rafaelDvl.orderservicemanager.DTOS;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rafaelDvl.orderservicemanager.domain.OS;
import com.rafaelDvl.orderservicemanager.domain.enuns.Prioridade;
import com.rafaelDvl.orderservicemanager.domain.enuns.Status;
import jakarta.validation.constraints.NotEmpty;

import java.io.Serializable;
import java.time.LocalDateTime;

public class OSDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm") private LocalDateTime dataAbertura;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm") private LocalDateTime dataFechamento;
    private Integer prioridade;
    @NotEmpty(message = "O campo observacoes e obrigatorio.") private String observacoes;
    private Integer status;

    private Integer tecnico;
    private Integer cliente;

    public OSDTO() {
    }

    public OSDTO(OS os) {
        this.id = os.getId();
        this.dataAbertura = os.getDataAbertura();
        this.dataFechamento = os.getDataFechamento();
        this.prioridade = os.getPrioridade().getCod();
        this.observacoes = os.getObservacoes();
        this.status = os.getStatus().getCod();
        this.tecnico = os.getTecnico().getId();
        this.cliente = os.getCliente().getId();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDateTime dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public LocalDateTime getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(LocalDateTime dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    public Prioridade getPrioridade() {
        return Prioridade.toEnum(this.prioridade);
    }

    public void setPrioridade(Integer prioridade) {
        this.prioridade = prioridade;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Status getStatus() { return Status.toEnum(this.status);
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getTecnico() {
        return tecnico;
    }

    public void setTecnico(Integer tecnico) {
        this.tecnico = tecnico;
    }

    public Integer getCliente() {
        return cliente;
    }

    public void setCliente(Integer cliente) {
        this.cliente = cliente;
    }
}
