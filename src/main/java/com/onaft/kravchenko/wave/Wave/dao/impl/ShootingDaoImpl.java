package com.onaft.kravchenko.wave.Wave.dao.impl;

import com.onaft.kravchenko.wave.Wave.dao.ShootingDao;
import com.onaft.kravchenko.wave.Wave.model.Account;
import com.onaft.kravchenko.wave.Wave.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
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
        String sql = "SELECT even.id_event, even.name, even.description, even.date_start, even.date_end, even.address\n" +
                "FROM public.events even, public.contracts con, public.shooting sh, public.shooting_groups shg\n" +
                "Where CURRENT_DATE < even.date_start and\n" +
                "(shg.id_employee = "+id_employee+" and shg.id_shooting = sh.id_shooting and \n" +
                " sh.id_shooting = con.id_shooting and con.id_event = even.id_event)";
        List<Event> events = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(Event.class));
        return events;
    }
}
