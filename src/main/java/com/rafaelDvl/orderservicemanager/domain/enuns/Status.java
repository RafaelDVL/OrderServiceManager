package com.rafaelDvl.orderservicemanager.domain.enuns;

public enum Status {

    ABERTO(0, "ABERTO"),
    ANDAMENTO(1, "ANDAMENTO"),
    ENCERRADO(2, "ENCERRADO");

    private Integer cod;
    private String desc;

    Status(Integer cod, String desc) {
        this.cod = cod;
        this.desc = desc;
    }

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static Status toEnum(Integer cod){
        if(cod == null){
            return null;
        }
        for(Status status : Status.values()){
            if(cod.equals(status.getCod())){
                return status;
            }
        }
        throw new IllegalArgumentException("Status invalido!!!" + cod);
    }
}
