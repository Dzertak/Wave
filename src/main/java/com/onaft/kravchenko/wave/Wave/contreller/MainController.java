package com.onaft.kravchenko.wave.Wave.contreller;

import com.onaft.kravchenko.wave.Wave.model.Account;
import com.onaft.kravchenko.wave.Wave.service.AccountService;
import com.onaft.kravchenko.wave.Wave.service.impl.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/entities")
public class MainController {

    @Autowired
    AccountServiceImpl accountService;

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
    method = RequestMethod.POST,
    produces = "application/json")
    public Account auth(@RequestParam("login") String login, @RequestParam("password") String password){
       return accountService.getAccountAuthorization(login,password);
    }

}
