package com.onaft.kravchenko.wave.Wave.dao.impl;

import com.onaft.kravchenko.wave.Wave.dao.ShootingDao;
import com.onaft.kravchenko.wave.Wave.model.*;
import com.onaft.kravchenko.wave.Wave.service.ShootingService;
import com.onaft.kravchenko.wave.Wave.util.EventRowMapper;
import com.onaft.kravchenko.wave.Wave.util.ShootingGroupRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
                "Where CURRENT_DATE < to_date(sh.date_start, 'YYYY-MM-DD HH24:MI:SS') and\n" +
                " (shg.id_employee = "+id_employee+" and shg.id_shooting = sh.id_shooting and\n" +
                "  tsh.id_type_shooting=sh.id_type_shooting and sh.id_shooting = con.id_shooting and con.id_event = even.id_event)";
        List<Event> events = getJdbcTemplate().query(sql, new EventRowMapper());
        return events;
    }


    @Override
    public List<Event> findShootingAll() {
        String sql = "SELECT even.description, even.id_event, even.name, even.address, even.id_customer,\n" +
                "sh.id_shooting, sh.id_type_shooting, sh.purpose, sh.date_start, sh.date_end,\n" +
                "tsh.name as type_shooting_name\n" +
                "FROM public.events even, public.contracts con, public.shooting sh, public.types_shooting tsh\n" +
                "Where CURRENT_DATE < to_date(sh.date_start, 'YYYY-MM-DD HH24:MI:SS') and\n" +
                " (tsh.id_type_shooting=sh.id_type_shooting and sh.id_shooting = con.id_shooting and con.id_event = even.id_event)";
        List<Event> events = getJdbcTemplate().query(sql, new EventRowMapper());
        return events;
    }

    @Override
    public List<Event> findEvents() {
        String sql = "SELECT even.description, even.id_event, even.name, even.address, even.id_customer \n" +
                "FROM public.events even";
                List<Event> events = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(Event.class));
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
                        customer1.setAddress(resultSet.getString(3));

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
        String sql = "SELECT s.id_shooting, s.id_type_shooting, s.purpose, ts.name, s.date_start, s.date_end\n" +
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
                        shooting1.setDate_start(resultSet.getString(5));
                        shooting1.setDate_end(resultSet.getString(6));
                        return shooting1;
                    }
                });

        return shooting;
    }

    @Override
    public List<Employee> findEmployeeByShooting(int id_shooting) {
        String sql = "SELECT e.id_employee, e.name, e.phone, e.address, e.code_pas, e.code_ident, e.id_position\n" +
                "\tFROM public.employees e, public.shooting_groups sg, public.shooting s\n" +
                "\twhere s.id_shooting = 1 and sg.id_shooting = s.id_shooting and e.id_employee = sg.id_employee;";
        List<Employee> employees = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(Employee.class));
        return employees;
    }

    @Override
    public Customer addCustomer(Customer customer) {

        final String INSERT_SQL = "INSERT INTO customers " +
        "(name, phone, address) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        getJdbcTemplate().update(
                new PreparedStatementCreator() {
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement ps =
                                connection.prepareStatement(INSERT_SQL, new String[] {"id_customer"});
                        ps.setString(1, customer.getName());
                        ps.setString(2, customer.getPhone());
                        ps.setString(3, customer.getAddress());
                        return ps;
                    }
                },
                keyHolder);
        customer.setId_customer(keyHolder.getKey().intValue());
        return customer;
    }

    @Override
    public Event addEvent(Event event) {
        String sql = "INSERT INTO events " +
                "(description, name, address, id_customer) VALUES (?, ?, ?, ?)" ;
        KeyHolder keyHolder = new GeneratedKeyHolder();
        getJdbcTemplate().update(
                new PreparedStatementCreator() {
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement ps =
                                connection.prepareStatement(sql, new String[] {"id_event"});
                        ps.setString(1, event.getDescription());
                        ps.setString(2, event.getName());
                        ps.setString(3, event.getAddress());
                        ps.setInt(4, event.getId_customer());
                        return ps;
                    }
                },
                keyHolder);
        event.setId_event(keyHolder.getKey().intValue());
        return event;
    }

    @Override
    public Shooting addShooting(Shooting shooting) {
        String sql = "INSERT INTO shooting " +
                "(id_type_shooting, purpose, date_start, date_end) VALUES (?, ?, ?, ?)" ;
        KeyHolder keyHolder = new GeneratedKeyHolder();
        getJdbcTemplate().update(
                new PreparedStatementCreator() {
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement ps =
                                connection.prepareStatement(sql, new String[] {"id_shooting"});
                        ps.setInt(1, shooting.getTypeShooting().getId_type_shooting());
                        ps.setString(2, shooting.getPurpose());
                        ps.setString(3, shooting.getDate_start());
                        ps.setString(4, shooting.getDate_end());
                        return ps;
                    }
                },
                keyHolder);
        shooting.setId_shooting(keyHolder.getKey().intValue());
        return shooting;
    }

    @Override
    public Contract addContract(Contract contract) {
        String sql = "INSERT INTO contracts " +
                "(id_event,id_shooting) VALUES (?, ?)" ;
        KeyHolder keyHolder = new GeneratedKeyHolder();
        getJdbcTemplate().update(
                new PreparedStatementCreator() {
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement ps =
                                connection.prepareStatement(sql, new String[] {"id_contract"});
                        ps.setInt(1, contract.getId_event());
                        ps.setInt(2, contract.getId_shooting());
                        return ps;
                    }
                },
                keyHolder);
        contract.setId_contract(keyHolder.getKey().intValue());
        return contract;
    }

    @Override
    public String addShootingGroup(ShootingGroupRequest groupRequest) {
        try {
            String sql = "INSERT INTO shooting_groups " +
                    "(id_shooting, id_employee) VALUES (?, ?)";
            getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {

                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    ps.setLong(1, groupRequest.getId_shooting());
                    ps.setInt(2, groupRequest.getEmployees().get(i).getId_employee());
                }

                @Override
                public int getBatchSize() {
                    return groupRequest.getEmployees().size();
                }
            });
            return "Done";
        } catch (Exception e){
            e.printStackTrace();
            return "Error";
        }
    }

    @Override
    public String deleteShooting(String id_shooting) {
        String sql = "DELETE FROM shooting WHERE id_shooting = "+id_shooting+";";
        int res =  getJdbcTemplate().update(sql, new Object[]{});
        if (res==1)
            return "Done";
        return "Error";
    }


}
