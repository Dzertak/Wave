package com.onaft.kravchenko.wave.Wave.service.impl;

import com.onaft.kravchenko.wave.Wave.dao.ShootingDao;
import com.onaft.kravchenko.wave.Wave.model.*;
import com.onaft.kravchenko.wave.Wave.service.ShootingService;
import com.onaft.kravchenko.wave.Wave.util.ShootingGroupRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShootingServiceImpl implements ShootingService {

    @Autowired
    ShootingDao shootingDao;

    @Override
    public List<Event> findShooting(String id_employee) {
        return shootingDao.findShooting(id_employee);
    }

    @Override
    public List<Event> findShootingAll() {
        return shootingDao.findShootingAll();
    }

    @Override
    public List<Event> findEvents() {
        return shootingDao.findEvents();
    }

    @Override
    public List<TypeShooting> findTypeShootingAll() {
        return shootingDao.findTypeShootingAll();
    }

    @Override
    public List<Employee> findEmployeeAll() {
        return shootingDao.findEmployeeAll();
    }

    @Override
    public List<Customer> findCustomerAll() {
        return shootingDao.findCustomerAll();
    }

    @Override
    public Customer findCustomerByShooting(int id_shooting) {
        return shootingDao.findCustomerByShooting(id_shooting);
    }

    @Override
    public Contract findContractByShooting(int id_shooting) {
        return shootingDao.findContractByShooting(id_shooting);
    }

    @Override
    public Shooting findShootingByShooting(int id_shooting) {
        return shootingDao.findShootingByShooting(id_shooting);
    }

    @Override
    public List<Employee> findEmployeeByShooting(int id_shooting) {
        return shootingDao.findEmployeeByShooting(id_shooting);
    }

    @Override
    public Customer addCustomer(Customer customer) {
        return shootingDao.addCustomer(customer);
    }

    @Override
    public Event addEvent(Event event) {
        return shootingDao.addEvent(event);
    }

    @Override
    public Shooting addShooting(Shooting shooting) {
        return shootingDao.addShooting(shooting);
    }

    @Override
    public Contract addContract(Contract contract) {
        return shootingDao.addContract(contract);
    }

    @Override
    public void addShootingGroup(ShootingGroupRequest groupRequest) {
        shootingDao.addShootingGroup(groupRequest);
    }

    @Override
    public String deleteShooting(String id_shooting) {
        return shootingDao.deleteShooting(id_shooting);
    }
}
