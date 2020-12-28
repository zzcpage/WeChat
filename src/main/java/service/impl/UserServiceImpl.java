package service.impl;

import dao.*;
import domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import service.GroupService;
import service.UserService;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    @Qualifier("userDao")
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
        ArrayList<GroupMessage> groups = groupService.getGroups(uid);
        UserMessage ret = new UserMessage(user,groups);
        return ret;
    }

    @Override
    public UserMessage findUserByAccount(String account) {
        //获取user
        User user = userDao.getUserByAccount(account);
        if(user == null)return null;
        ArrayList<GroupMessage> groups = groupService.getGroups(user.getUid());
        UserMessage ret = new UserMessage(user,groups);
        return ret;
    }

    @Override
    public User getUser(Long uid) {
        return userDao.getUserById(uid);
    }

    @Override
    public boolean isValid(String account, String password) {
        User user = userDao.getUser(account, password);
        return user != null;
    }


    public boolean isValidAccount(String account) {
        User user = userDao.getUserByAccount(account);
        return user != null;
    }

    @Override
    public void addUser(User user) {
        userDao.insertUser(user);
        User user02 = userDao.getUserByAccount(user.getAccount());
        Group defaultGroup = new Group("我的好友", user02.getUid());
        groupService.addGroup(defaultGroup);
    }

    @Override
    public void updataUser(User user) {
        userDao.updateUser(user);
    }


    @Override
    public boolean isOnline(Long uid) {
        User user = userDao.getUserById(uid);
        if(user == null)return false;
        return user.getState() == 1;
    }

    @Override
    public UserMessage login(String account, String password) {
        User user = userDao.getUser(account, password);
        if(user == null)return null;
        //user.setState(1);
        //userDao.updateUserState(user.getUid(), 1);
        //ArrayList<GroupMessage> groups = groupService.getGroups(user.getUid());
        UserMessage ret = new UserMessage(user,null);
        return ret;
    }

    @Override
    public void logout(long uid) {
        userDao.updateUserState(uid, 0);
    }

    @Override
    public String register(User user) {
        long time = new Date().getTime();
        String account = "";
        Random random = new Random();
        boolean hasAccount = true;
        while(hasAccount) {
            int x = random.nextInt(9) + 1;
            account += x;
            for(int i = 0; i < 9; i++) {
                account+=random.nextInt(10);
            }
            hasAccount = isValidAccount(account);
        }
        user.setAccount(account);
        addUser(user);
        return account;
    }

    @Override
    public void changeState(Long uid,int state){
        User p = userDao.getUserById(uid) ;
        p.setState(state);
        userDao.updateUserState(uid, state) ;
    }

}
