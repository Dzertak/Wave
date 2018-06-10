package com.onaft.kravchenko.wave.Wave.dao.impl;

import com.onaft.kravchenko.wave.Wave.dao.ShootingDao;
import com.onaft.kravchenko.wave.Wave.model.*;
import com.onaft.kravchenko.wave.Wave.service.ShootingService;
import com.onaft.kravchenko.wave.Wave.util.EventRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Repository
public class ShootingDaoImpl extends JdbcDaoSupport implements ShootingDao {

    @Qualifier("dataSource")
    @Autowired
    DataSource dataSource;

    @PostConstruct
    private void initialize(){
        setDataSource(dataSource);
    }

    @Override
    public List<Event> findShooting(String id_employee) {
        String sql = "SELECT even.description, even.id_event, even.name, even.address, even.id_customer,\n" +
                "sh.id_shooting, sh.id_type_shooting, sh.purpose, sh.date_start, sh.date_end,\n" +
                "tsh.name as type_shooting_name\n" +
                "FROM public.events even, public.contracts con, public.shooting sh, public.shooting_groups shg, public.types_shooting tsh\n" +
                "Where CURRENT_DATE < sh.date_start and\n" +
                " (shg.id_employee = "+id_employee+" and shg.id_shooting = sh.id_shooting and\n" +
                "  tsh.id_type_shooting=sh.id_type_shooting and sh.id_shooting = con.id_shooting and con.id_event = even.id_event)";
        List<Event> events = getJdbcTemplate().query(sql, new EventRowMapper());
        return events;
    }

    @Override
    public String findShootingFullInfo(String id_employee) {
        String sql = "SELECT even.id_event, even.name, even.description, even.date_start, even.date_end, even.address\n" +
                "FROM public.events even, public.contracts con, public.shooting sh, public.shooting_groups shg\n" +
                "Where CURRENT_DATE < even.date_start and\n" +
                "(shg.id_employee = "+id_employee+" and shg.id_shooting = sh.id_shooting and \n" +
                " sh.id_shooting = con.id_shooting and con.id_event = even.id_event)";
        return null;
    }

    @Override
    public List<Event> findShootingAll() {
        String sql = "SELECT even.description, even.id_event, even.name, even.address, even.id_customer,\n" +
                "sh.id_shooting, sh.id_type_shooting, sh.purpose, sh.date_start, sh.date_end,\n" +
                "tsh.name as type_shooting_name\n" +
                "FROM public.events even, public.contracts con, public.shooting sh, public.types_shooting tsh\n" +
                "Where CURRENT_DATE < sh.date_start and\n" +
                " (tsh.id_type_shooting=sh.id_type_shooting and sh.id_shooting = con.id_shooting and con.id_event = even.id_event)";
        List<Event> events = getJdbcTemplate().query(sql, new EventRowMapper());
        return events;
    }

    @Override
    public List<TypeShooting> findTypeShootingAll() {
        String sql = "SELECT * FROM types_shooting";
        List<TypeShooting> typeShootings = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(TypeShooting.class));
        return typeShootings;
    }

    @Override
    public List<Employee> findEmployeeAll() {
        String sql = "SELECT *" +
                " FROM employees";
        List<Employee> employees = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(Employee.class));
        return employees;
    }

    @Override
    public List<Customer> findCustomerAll() {
        String sql = "SELECT * FROM customers";
        List<Customer> customers = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(Customer.class));
        return customers;
    }

    @Override
    public Customer findCustomerByShooting(int id_shooting) {
        String sql = "SELECT cus.id_customer, cus.name, cus.phone, cus.address\n" +
                "\tFROM public.customers cus, public.contracts con, public.events e\n" +
                "\tWHERE con.id_shooting = "+String.valueOf(id_shooting)+" and con.id_event=e.id_event and e.id_customer = cus.id_customer;";
        Customer customer = getJdbcTemplate().queryForObject(
                sql, new Object[]{}, new RowMapper<Customer>() {
                    @Override
                    public Customer mapRow(ResultSet resultSet, int i) throws SQLException {
                        Customer customer1 = new Customer();
                        customer1.setId_customer(resultSet.getInt(1));
                        customer1.setName(resultSet.getString(2));
                        customer1.setPhone(resultSet.getString(4));
                        customer1.setAdress(resultSet.getString(3));

                        return customer1;
                    }
                });
        return customer;
    }

    @Override
    public Contract findContractByShooting(int id_shooting) {
        String sql = "SELECT id_contract, id_event, id_shooting\n" +
                "\tFROM public.contracts\n" +
                "\tWhere id_shooting = "+String.valueOf(id_shooting)+";";
        Contract contract = getJdbcTemplate().queryForObject(
                sql, new Object[]{}, new RowMapper<Contract>() {
                    @Override
                    public Contract mapRow(ResultSet resultSet, int i) throws SQLException {
                        Contract contract1 = new Contract();
                        contract1.setId_contract(resultSet.getInt(1));
                        contract1.setId_event(resultSet.getInt(2));
                        contract1.setId_shooting(resultSet.getInt(3));
                        return contract1;
                    }
                });
        return contract;
    }

    @Override
    public Shooting findShootingByShooting(int id_shooting) {
        String sql = "SELECT s.id_shooting, s.id_type_shooting, s.purpose, ts.name\n" +
                "\tFROM public.shooting as s, public.types_shooting ts\n" +
                "\tWHERE "+String.valueOf(id_shooting)+" = s.id_shooting and s.id_type_shooting = ts.id_type_shooting;";
        Shooting shooting = getJdbcTemplate().queryForObject(
                sql, new Object[]{}, new RowMapper<Shooting>() {
                    @Override
                    public Shooting mapRow(ResultSet resultSet, int i) throws SQLException {
                        Shooting shooting1 = new Shooting();
                        shooting1.setId_shooting(resultSet.getInt(1));
                        TypeShooting typeShooting = new TypeShooting();
                        typeShooting.setId_type_shooting(resultSet.getInt(2));
                        shooting1.setPurpose(resultSet.getString(3));
                        typeShooting.setName(resultSet.getString(4));
                        shooting1.setTypeShooting(typeShooting);
                        return shooting1;
                    }
                });

        return shooting;
    }

    @Override
    public List<Employee> findEmployeeByShooting(int id_shooting) {
        String sql = "SELECT e.id_employee, e.name, e.phone, e.date_birthday, e.address, e.date_reg, e.code_pas, e.code_ident, e.id_position\n" +
                "\tFROM public.employees e, public.shooting_groups sg, public.shooting s\n" +
                "\twhere s.id_shooting = 1 and sg.id_shooting = s.id_shooting and e.id_employee = sg.id_employee;";
        List<Employee> employees = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(Employee.class));
        return employees;
    }


}
