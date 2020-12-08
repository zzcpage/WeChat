package service;

import domain.User;
import org.springframework.stereotype.Service;

public interface UserService {

    /**
     * 通过uid查找用户
     * @param uid 用户uid
     * @return
     */
    public User findUserByUId(Integer uid);

    /**
     * 通过账号查找用户
     * @param account
     * @return
     */
    public User findUserByAccount(String account);

    /**
     * 判断账号密码是否有效
     * @param account
     * @param password
     * @return
     */
    public boolean isValid(String account, String password);


    /**
     * 添加账号
     */
    public void addUser(User user);


    /**
     * 更新用户信息
     * @param user
     */
    public void updataUser(User user);


    /**
     * 判断用户是否在线
     * @return
     */
    public boolean isOnline();
}
