package service.impl;

import domain.User;
import domain.UserMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.UserService;

import java.security.Provider;
import java.sql.Date;
import java.time.LocalDate;

import static org.junit.Assert.*;

@ContextConfiguration("classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceImplTest {
    @Autowired
    UserService userService;
    @Test
    public void findUserByUId() {
        System.out.println(userService.findUserByUId(4l));;
    }

    @Test
    public void findUserByAccount() {
        System.out.println(userService.findUserByAccount("123456789"));
    }

    @Test
    public void isValid() {
        boolean valid = userService.isValid("1", "1");
        System.out.println(valid);
    }

    @Test
    public void isValidAccount() {
        System.out.println(userService.isValidAccount("1"));
    }

    @Test
    public void addUser() {
        User user = new User("u3", "93456789", "12345678", "123@qq.com", "男", Date.valueOf(LocalDate.now()), "aaa", "imgSrc", 0);
        userService.addUser(user);
    }

    @Test
    public void updataUser() {
        User user = new User("123", "123456789", "912345678", "123@qq.com", "男", Date.valueOf(LocalDate.now()), "aaa", "imgSrc", 0);
        user.setUid(5l);
        userService.updataUser(user);
    }

    @Test
    public void isOnline() {
        System.out.println(new java.util.Date().getTime());
    }

    @Test
    public void login() {
        UserMessage login = userService.login("123456789", "12345678");
        System.out.println(login);
    }

    @Test
    public void logout() {
        userService.logout(15l);
    }

    @Test
    public void register() {
        String str = userService.register(new User("u4", "1", "1abcdefg", "12345@qq.com", "男", new java.util.Date(), "", "imgSrc", 0));
        System.out.println(str);
    }
}