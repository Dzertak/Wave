package com.onaft.kravchenko.wave.Wave.service;

import com.onaft.kravchenko.wave.Wave.model.Account;
import com.onaft.kravchenko.wave.Wave.model.Event;

import java.util.List;

public interface ShootingService {
    List<Event> findShooting(String id_employee);
}
