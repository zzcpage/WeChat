package service.impl;

import domain.FriendMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.FriendService;

import static org.junit.Assert.*;
@ContextConfiguration("classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class FriendServiceImplTest {

    @Autowired
    private FriendService friendService;

    @Test
    public void updataFriendInfo() {
        friendService.updataFriendInfo(15l, 16l, 7l);
    }

    @Test
    public void deleteFriendById() {
        friendService.deleteFriendById(15l, 16l);
    }

    @Test
    public void addFriend() {

    }

    @Test
    public void getFriendById() {
        FriendMessage friendById = friendService.getFriendById(15l, 17l);
        System.out.println(friendById);
    }
}