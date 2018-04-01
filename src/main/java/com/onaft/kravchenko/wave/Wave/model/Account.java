package com.onaft.kravchenko.wave.Wave.model;

import java.io.Serializable;

public class Account implements Serializable{
    private int id_account;
    private String login;
    private String password;

    public Account(int id_account, String login, String password) {
        this.id_account = id_account;
        this.login = login;
        this.password = password;
    }

    public int getId_account() {
        return id_account;
    }

    public void setId_account(int id_account) {
        this.id_account = id_account;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id_account=" + id_account +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
