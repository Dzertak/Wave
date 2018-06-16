package com.onaft.kravchenko.wave.Wave.service;

import com.onaft.kravchenko.wave.Wave.model.*;
import com.onaft.kravchenko.wave.Wave.util.ShootingGroupRequest;

import java.util.List;

public interface ShootingService {
    List<Event> findShooting(String id_employee);
    List<Event> findShootingAll();
    List<Event> findEvents();
    List<TypeShooting> findTypeShootingAll();
    List<Employee> findEmployeeAll();
    List<Customer> findCustomerAll();
    Customer findCustomerByShooting(int id_shooting);
    Contract findContractByShooting(int id_shooting);
    Shooting findShootingByShooting(int id_shooting);
    List<Employee> findEmployeeByShooting(int id_shooting);
    Customer addCustomer(Customer customer);
    Event addEvent(Event event);
    Shooting addShooting(Shooting shooting);
    Contract addContract(Contract contract);
    String addShootingGroup(ShootingGroupRequest groupRequest);
    String deleteShooting(String id_shooting);
}
