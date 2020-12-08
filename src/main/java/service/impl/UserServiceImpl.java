package service.impl;

import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import service.UserService;

public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User findUserByUId(Integer uid) {
        return null;
    }

    @Override
    public User findUserByAccount(String account) {
        return null;
    }

    @Override
    public boolean isValid(String account, String password) {
        return false;
    }

    @Override
    public void addUser(User user) {

    }

    @Override
    public void updataUser(User user) {

    }

    @Override
    public boolean isOnline() {
        return false;
    }
}
