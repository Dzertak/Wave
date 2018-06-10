package com.onaft.kravchenko.wave.Wave.model;

import java.io.Serializable;

public class Contract implements Serializable{
    private int id_contract;
    private int id_event;
    private int id_shooting;

    public Contract(){}
    public Contract(int id_contract, int id_event, int id_shooting) {
        this.id_contract = id_contract;
        this.id_event = id_event;
        this.id_shooting = id_shooting;
    }

    public int getId_contract() {
        return id_contract;
    }

    public void setId_contract(int id_contract) {
        this.id_contract = id_contract;
    }

    public int getId_event() {
        return id_event;
    }

    public void setId_event(int id_event) {
        this.id_event = id_event;
    }

    public int getId_shooting() {
        return id_shooting;
    }

    public void setId_shooting(int id_shooting) {
        this.id_shooting = id_shooting;
    }

}
