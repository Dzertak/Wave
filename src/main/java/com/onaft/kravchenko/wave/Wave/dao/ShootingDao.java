package com.onaft.kravchenko.wave.Wave.dao;

import com.onaft.kravchenko.wave.Wave.model.Account;
import com.onaft.kravchenko.wave.Wave.model.Event;

import java.util.List;

public interface ShootingDao {
    List<Event> findShooting(String v);
}
