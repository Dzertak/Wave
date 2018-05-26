package com.onaft.kravchenko.wave.Wave.dao;

import com.onaft.kravchenko.wave.Wave.model.Account;

import java.util.List;

public interface AccountDao {
    void insert(Account account);
    void inserBatch(List<Account> accounts);
    List<Account> loadAllAccount();
    Account findAccountById(long id_account);
    Account findAccountAuthorization(String login, String password);
    String findLoginById(long id_account);
    int getTotalNumberAccount();
}
