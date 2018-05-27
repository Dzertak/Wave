package com.onaft.kravchenko.wave.Wave.dao.impl;

import com.onaft.kravchenko.wave.Wave.dao.AccountDao;
import com.onaft.kravchenko.wave.Wave.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import javax.annotation.PostConstruct;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    public Account findAccountAuthorization(String login, String password) {
        String sql = "Select * from accounts where login='"+login+"' and password='"+password+"'";
        //Account account =  getJdbcTemplate().queryForObject(sql,Account.class);
        List<Account> strLst  = getJdbcTemplate().query(sql, new RowMapper<Account>() {
            @Override
            public Account mapRow(ResultSet resultSet, int i) throws SQLException {
                Account account = new Account();
                account.setId_account(resultSet.getInt(1));
                account.setId_employee(resultSet.getInt(2));
                account.setId_type_access(resultSet.getInt(3));
                account.setLogin(resultSet.getString(4));
                account.setPassword(resultSet.getString(5));
                return account;
            }
        });

        if ( strLst.isEmpty() ){
            return null;
        }else if ( strLst.size() == 1 ) { // list contains exactly 1 element
            return strLst.get(0);
        }else{  // list contains more than 1 elements
            //your wish, you can either throw the exception or return 1st element.
            System.out.println("/////Many fields");
        }
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
