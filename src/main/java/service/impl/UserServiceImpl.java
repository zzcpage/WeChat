package service.impl;

import dao.*;
import domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.GroupService;
import service.UserService;

import java.util.ArrayList;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private GroupDao groupDao;

    @Autowired
    private FriendDao friendDao;

    @Autowired
    private MessageDao messageDao;

    @Autowired
    private GroupService groupService;

    @Override
    public UserMessage findUserByUId(Long uid) {
        //获取user
        User user = userDao.getUserById(uid);
        ArrayList<GroupMessage> groups = groupService.getGroups(user);
        UserMessage ret = new UserMessage(user,groups);
        return ret;
    }

    @Override
    public UserMessage findUserByAccount(String account) {
        //获取user
        User user = userDao.getUserByAccount(account);
        ArrayList<GroupMessage> groups = groupService.getGroups(user);
        UserMessage ret = new UserMessage(user,groups);
        return ret;
    }

    @Override
    public boolean isValid(String account, String password) {
        User user = userDao.getUser(account, password);
        return user == null;
    }


    public boolean isValidAccount(String account) {
        User user = userDao.getUserByAccount(account);
        return user == null;
    }

    @Override
    public void addUser(User user) {
        userDao.insertUser(user);
    }

    @Override
    public void updataUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public void updataUser(UserMessage userMessage) {
        userDao.updateUser(userMessage);
    }

    @Override
    public boolean isOnline(Long uid) {
        User user = userDao.getUserById(uid);
        if(user == null)return false;
        return user.getStatus() == 1;
    }


}
