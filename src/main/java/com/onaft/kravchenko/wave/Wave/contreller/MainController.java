package com.onaft.kravchenko.wave.Wave.contreller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.onaft.kravchenko.wave.Wave.model.*;
import com.onaft.kravchenko.wave.Wave.service.AccountService;
import com.onaft.kravchenko.wave.Wave.service.impl.AccountServiceImpl;
import com.onaft.kravchenko.wave.Wave.service.impl.ShootingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.List;

@RestController
@RequestMapping("/entities")
public class MainController {

    @Autowired
    AccountServiceImpl accountService;

    @Autowired
    ShootingServiceImpl shootingService;

    @RequestMapping(value = "/getHelloWorld",
                    method = RequestMethod.GET,
                    produces = "application/json")
    public String getHelloWorld() {
        return "Hello World1";
    }

    @RequestMapping(value = "/insertAccount",
            method = RequestMethod.POST,
            produces = "application/json")
    public String insertAccount(@RequestBody Account account) {
        accountService.insert(account);
        return "done";
    }

    @RequestMapping(value = "/authorize",
    method = RequestMethod.GET,
    produces = "application/json")
    public Account auth(@RequestParam("login") String login, @RequestParam("password") String password){
       return accountService.getAccountAuthorization(login,password);
    }

    @RequestMapping(value = "/shootingEvents",
            method = RequestMethod.GET,
            produces = "application/json")
    public List<Event> shootingEvent(@RequestParam("id_employee") String id_employee){
        return shootingService.findShooting(id_employee);
    }

    @RequestMapping(value = "/shootingEventsAll",
            method = RequestMethod.GET,
            produces = "application/json")
    public List<Event> shootingEventAll(){
        return shootingService.findShootingAll();
    }

    @RequestMapping(value = "/findEvents",
            method = RequestMethod.GET,
            produces = "application/json")
    public List<Event> findEvents(){
        return shootingService.findEvents();
    }

    @RequestMapping(value = "/typeShootingAll",
            method = RequestMethod.GET,
            produces = "application/json")
    public List<TypeShooting> findTypeShootingALL(){
        return shootingService.findTypeShootingAll();
    }

    @RequestMapping(value = "/employeesAll",
            method = RequestMethod.GET,
            produces = "application/json")
    public List<Employee> employeesAll(){
        return shootingService.findEmployeeAll();
    }

    @RequestMapping(value = "/customersAll",
            method = RequestMethod.GET,
            produces = "application/json")
    public List<Customer> customersAll(){
        return shootingService.findCustomerAll();
    }

    @RequestMapping(value = "/shootingByIdShooting",
            method = RequestMethod.GET,
            produces = "application/json")
    public Shooting shootingByIdShooting(@RequestParam("id_shooting") int id_shooting){
        return shootingService.findShootingByShooting(id_shooting);
    }

    @RequestMapping(value = "/customerByIdShooting",
            method = RequestMethod.GET,
            produces = "application/json")
    public Customer customerByIdShooting(@RequestParam("id_shooting") int id_shooting){
        return shootingService.findCustomerByShooting(id_shooting);
    }

    @RequestMapping(value = "/contractByIdShooting",
            method = RequestMethod.GET,
            produces = "application/json")
    public Contract contractByIdShooting(@RequestParam("id_shooting") int id_shooting){
        return shootingService.findContractByShooting(id_shooting);
    }

    @RequestMapping(value = "/employeesByIdShooting",
            method = RequestMethod.GET,
            produces = "application/json")
    public List<Employee> employeesByIdShooting(@RequestParam("id_shooting") int id_shooting){
        return shootingService.findEmployeeByShooting(id_shooting);
    }

    @RequestMapping(value = "/addCustomer",
            method = RequestMethod.POST,
            produces = "application/json")
    public Customer addCustomer(@RequestBody Customer customer){
        return shootingService.addCustomer(customer);
    }

    @RequestMapping(value = "/addEvent",
            method = RequestMethod.POST,
            produces = "application/json")
    public Event addEvent(@RequestBody Event event){
        return shootingService.addEvent(event);
    }

    @RequestMapping(value = "/addShooting",
            method = RequestMethod.POST,
            produces = "application/json")
    public Shooting addShooting(@RequestBody String shooting){
        Type type = new TypeToken<Shooting>() {
        }.getType();
        Gson gson  = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss.SSS").create();
        Shooting shooting1 = gson.fromJson(shooting, type);
        return shootingService.addShooting(shooting1);
    }

    @RequestMapping(value = "/addShootingGroup",
            method = RequestMethod.POST,
            produces = "application/json")
    public void addShootingGroup(@RequestParam("id_shooting") int id_shooting, @RequestBody List<Employee> employees){
        shootingService.addShootingGroup(id_shooting,employees);
    }

    @RequestMapping(value = "/addContract",
            method = RequestMethod.POST,
            produces = "application/json")
    public Contract addContract(@RequestBody Contract contract){
        return shootingService.addContract(contract);
    }

    @RequestMapping(value = "/deleteShooting",
            method = RequestMethod.DELETE,
            produces = "application/json")
    public String deleteShooting(@RequestParam("id_shooting") String id_shooting){
        return shootingService.deleteShooting(id_shooting);
    }
}
