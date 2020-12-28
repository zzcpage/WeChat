package dao;

import domain.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Date;
import java.time.LocalDate;

import static org.junit.Assert.*;

public class UserDaoTest {

    @Test
    public void getUser() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserDao userDao = ac.getBean("userDao", UserDao.class);
        User user = new User("crk", "123456789", "123456", "1563157089@qq.com", "男",Date.valueOf(LocalDate.now()),"无敌是多么的寂寞" , "imgSrc", 1);
        userDao.insertUser(user);
        System.out.println();
    }

    @Test
    public void getUserById() {
    }

    @Test
    public void testGetUserById() {
    }

    @Test
    public void getUserByAccount() {
    }

    @Test
    public void insertUser() {
    }

    @Test
    public void updateUser() {
    }
}