package com.onaft.kravchenko.wave.Wave.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class Shooting implements Serializable {
    private int id_shooting;
    private TypeShooting typeShooting;
    private String purpose;
   // @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss.SSS")
    private Timestamp date_start;
    //@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss.SSS")
    private Timestamp date_end;

    public Shooting() {
    }

    public Shooting(int id_shooting, TypeShooting typeShooting, String purpose, Timestamp date_start, Timestamp date_end) {
        this.id_shooting = id_shooting;
        this.typeShooting = typeShooting;
        this.purpose = purpose;
        this.date_start = date_start;
        this.date_end = date_end;
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

    public Timestamp getDate_start() {
        return date_start;
    }

    public void setDate_start(Timestamp date_start) {
        this.date_start = date_start;
    }

    public Timestamp getDate_end() {
        return date_end;
    }

    public void setDate_end(Timestamp date_end) {
        this.date_end = date_end;
    }
}
