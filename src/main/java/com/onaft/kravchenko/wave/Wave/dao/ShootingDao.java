package com.onaft.kravchenko.wave.Wave.dao;

import com.onaft.kravchenko.wave.Wave.model.*;

import java.util.List;

public interface ShootingDao {
    List<Event> findShooting(String id_employee);
    String findShootingFullInfo(String id_employee);
    List<Event> findShootingAll();
    List<TypeShooting> findTypeShootingAll();
    List<Employee> findEmployeeAll();
    List<Customer> findCustomerAll();
    Customer findCustomerByShooting(int id_shooting);
    Contract findContractByShooting(int id_shooting);
    Shooting findShootingByShooting(int id_shooting);
    List<Employee> findEmployeeByShooting(int id_shooting);

}
