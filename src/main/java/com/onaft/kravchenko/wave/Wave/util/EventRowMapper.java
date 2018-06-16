package com.onaft.kravchenko.wave.Wave.util;

import com.onaft.kravchenko.wave.Wave.model.Event;
import com.onaft.kravchenko.wave.Wave.model.Shooting;
import com.onaft.kravchenko.wave.Wave.model.TypeShooting;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EventRowMapper implements RowMapper<Event> {
    @Override
    public Event mapRow(ResultSet resultSet, int i) throws SQLException {
        Event event = new Event();
        event.setDescription(resultSet.getString(1));
        event.setId_event(resultSet.getInt(2));
        event.setName(resultSet.getString(3));
        event.setAddress(resultSet.getString(4));
        event.setId_customer(resultSet.getInt(5));
        Shooting shooting = new Shooting();
        shooting.setId_shooting(resultSet.getInt(6));
        TypeShooting typeShooting = new TypeShooting();
        typeShooting.setId_type_shooting(resultSet.getInt(7));
        shooting.setPurpose(resultSet.getString(8));
        shooting.setDate_start(resultSet.getString(9));
        shooting.setDate_end(resultSet.getString(10));
        typeShooting.setName(resultSet.getString(11));
        shooting.setTypeShooting(typeShooting);
        event.setShooting(shooting);
        return event;
    }
}
