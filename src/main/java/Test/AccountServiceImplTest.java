package Test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.AccountService;

import static org.junit.Assert.*;

public class AccountServiceImplTest {

    @Test
    public void findAll() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        AccountService accountService = (AccountService) ac.getBean("accountService");
        accountService.findAll();
    }

    @Test
    public void saveAccount() {
    }
}