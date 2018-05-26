package com.onaft.kravchenko.wave.Wave.service;

import com.onaft.kravchenko.wave.Wave.model.Account;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountService {
    void insert(Account cus);
    void insertBatch(List<Account> accounts);
    void loadAllAccount();
    void getAccountById(long id_account);
    Account getAccountAuthorization(String login, String password);
    void getCustomerNameById(long id_account);
    void getTotalNumerAccount();
}
