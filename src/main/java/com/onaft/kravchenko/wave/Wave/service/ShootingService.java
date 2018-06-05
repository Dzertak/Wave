package com.onaft.kravchenko.wave.Wave.service;

import com.onaft.kravchenko.wave.Wave.model.*;

import java.util.List;

public interface ShootingService {
    List<Event> findShooting(String id_employee);
    List<Event> findShootingAll();
    List<TypeShooting> findTypeShootingAll();
    List<Employee> findEmployeeAll();
    List<Customer> findCustomerAll();
}
