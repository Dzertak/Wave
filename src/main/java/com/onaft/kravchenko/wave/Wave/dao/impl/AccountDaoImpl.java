package com.onaft.kravchenko.wave.Wave.dao.impl;

import com.onaft.kravchenko.wave.Wave.dao.AccountDao;
import com.onaft.kravchenko.wave.Wave.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import javax.annotation.PostConstruct;
import java.util.List;

@Repository
public class AccountDaoImpl extends JdbcDaoSupport implements AccountDao {

    @Qualifier("dataSource")
    @Autowired
    DataSource dataSource;

    @PostConstruct
    private void initialize(){
        setDataSource(dataSource);
    }

    @Override
    public void insert(Account account) {
        String sql = "INSERT INTO account " +
                "(id_account, login, pass) VALUES (?, ?, ?)" ;
        getJdbcTemplate().update(sql, new Object[]{
                account.getId_account(), account.getLogin(), account.getPassword()
        });
    }

    @Override
    public void inserBatch(List<Account> accounts) {

    }

    @Override
    public List<Account> loadAllAccount() {
        return null;
    }

    @Override
    public Account findAccountById(long id_account) {
        return null;
    }

    @Override
    public String findLoginById(long id_account) {
        return null;
    }

    @Override
    public int getTotalNumberAccount() {
        return 0;
    }
}
