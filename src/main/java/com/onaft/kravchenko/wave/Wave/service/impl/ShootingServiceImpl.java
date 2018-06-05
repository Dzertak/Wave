package com.onaft.kravchenko.wave.Wave.service.impl;

import com.onaft.kravchenko.wave.Wave.dao.ShootingDao;
import com.onaft.kravchenko.wave.Wave.model.*;
import com.onaft.kravchenko.wave.Wave.service.ShootingService;
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
}
