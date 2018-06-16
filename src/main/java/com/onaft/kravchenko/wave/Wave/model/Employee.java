package com.onaft.kravchenko.wave.Wave.model;

import java.io.Serializable;
import java.util.Date;

public class Employee implements Serializable {
    private int id_employee;
    private String name;
    private String code_pas;
    private String code_ident;
    private String phone;
    private String address;
    private int id_position;

    public Employee(){

    }

    public Employee(int id_employee, String name, String code_pas, String code_ident, String phone, String address, int id_position) {
        this.id_employee = id_employee;
        this.name = name;
        this.code_pas = code_pas;
        this.code_ident = code_ident;
        this.phone = phone;
        this.address = address;
        this.id_position = id_position;
    }

    public int getId_employee() {
        return id_employee;
    }

    public void setId_employee(int id_employee) {
        this.id_employee = id_employee;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode_pas() {
        return code_pas;
    }

    public void setCode_pas(String code_pas) {
        this.code_pas = code_pas;
    }

    public String getCode_ident() {
        return code_ident;
    }

    public void setCode_ident(String code_ident) {
        this.code_ident = code_ident;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getId_position() {
        return id_position;
    }

    public void setId_position(int id_position) {
        this.id_position = id_position;
    }
}
