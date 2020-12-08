package controller;

import domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import service.impl.AccountServiceImpl;

import java.util.List;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountServiceImpl accountService;

    @RequestMapping("/testSpringmvc")
    public String testSpringmvc() {
        System.out.println("springmvc is ok");
        accountService.findAll();
        return "list";
    }

    @RequestMapping("/findall")
    public String findall(Model model) {
        List<Account> all = accountService.findAll();
        model.addAttribute("list",all);
        return "list";
    }

    @RequestMapping("/save")
    public String save(Account account, Model model) {
        List<Account> all = accountService.findAll();
        model.addAttribute("list",all);
        accountService.saveAccount(account);
        return "list";
    }
}
