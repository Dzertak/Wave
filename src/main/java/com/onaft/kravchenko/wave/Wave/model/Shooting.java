package com.onaft.kravchenko.wave.Wave.model;

import java.io.Serializable;

public class Shooting implements Serializable {
    private int id_shooting;
    private TypeShooting typeShooting;
    private String purpose;

    public Shooting() {
    }

    public Shooting(int id_shooting, TypeShooting typeShooting, String purpose) {
        this.id_shooting = id_shooting;
        this.typeShooting = typeShooting;
        this.purpose = purpose;
    }

    public int getId_shooting() {
        return id_shooting;
    }

    public void setId_shooting(int id_shooting) {
        this.id_shooting = id_shooting;
    }

    public TypeShooting getTypeShooting() {
        return typeShooting;
    }

    public void setTypeShooting(TypeShooting typeShooting) {
        this.typeShooting = typeShooting;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }
}
