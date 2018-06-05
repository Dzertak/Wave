package com.onaft.kravchenko.wave.Wave.dao;

import com.onaft.kravchenko.wave.Wave.model.*;

import java.util.List;

public interface ShootingDao {
    List<Event> findShooting(String id_employee);
    List<Event> findShootingAll();
    List<TypeShooting> findTypeShootingAll();
    List<Employee> findEmployeeAll();
    List<Customer> findCustomerAll();
}
