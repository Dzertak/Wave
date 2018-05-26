package com.onaft.kravchenko.wave.Wave.service.impl;

import com.onaft.kravchenko.wave.Wave.dao.AccountDao;
import com.onaft.kravchenko.wave.Wave.model.Account;
import com.onaft.kravchenko.wave.Wave.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountDao accountDao;



    @Override
    public void insert(Account cus) {
        accountDao.insert(cus);
    }



    @Override
    public void insertBatch(List<Account> accounts) {

    }

    @Override
    public void loadAllAccount() {

    }

    @Override
    public void getAccountById(long id_account) {

    }

    @Override
    public Account getAccountAuthorization(String login, String password) {
        return accountDao.findAccountAuthorization(login,password);
    }

    @Override
    public void getCustomerNameById(long id_account) {

    }

    @Override
    public void getTotalNumerAccount() {

    }
}
