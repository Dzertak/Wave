package com.onaft.kravchenko.wave.Wave.contreller;

import com.onaft.kravchenko.wave.Wave.model.Account;
import com.onaft.kravchenko.wave.Wave.model.Event;
import com.onaft.kravchenko.wave.Wave.service.AccountService;
import com.onaft.kravchenko.wave.Wave.service.impl.AccountServiceImpl;
import com.onaft.kravchenko.wave.Wave.service.impl.ShootingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}
