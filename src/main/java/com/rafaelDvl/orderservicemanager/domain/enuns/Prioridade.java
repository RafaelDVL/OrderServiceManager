package com.rafaelDvl.orderservicemanager.domain.enuns;

public enum Prioridade {

    BAIXA(0, "BAIXA"),
    MEDIA(1, "MEDIA"),
    ALTA(2, "ALTA");

    private Integer cod;
    private String desc;


    Prioridade(int cod, String desc) {
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

    public static Prioridade toEnum(Integer cod){
        if(cod == null){
            return null;
        }
        for(Prioridade prioridade : Prioridade.values()){
            if(cod.equals(prioridade.getCod())){
                return prioridade;
            }
        }
        throw new IllegalArgumentException("Prioridade Invalida!");
    }
}
