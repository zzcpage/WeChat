package service;

import domain.User;
import domain.UserMessage;
import org.springframework.stereotype.Service;

public interface UserService {

    /**
     * 通过uid查找用户
     * @param uid 用户uid
     * @return 前端要用的用户所有信息
     */
    public UserMessage findUserByUId(Long uid);

    /**
     * 通过账号查找用户
     * @param account 用户账号
     * @return 前端要用的用户所有信息
     */
    public UserMessage findUserByAccount(String account);

    /**
     * 判断账号密码是否有效
     * @param account 账号
     * @param password 密码
     * @return true 表示有效 否则无效
     */
    public boolean isValid(String account, String password);

    /**
     * 判断账号是否存在
     * @param account 账号
     * @return true 表示已存在，false表示不存在
     */
    public boolean isValidAccount(String account);

    /**
     * 添加用户
     */
    public void addUser(User user);

    /**
     * 更新用户信息
     * @param user
     */
    public void updataUser(User user);

//    /**
//     * 更新用户信息
//     * @param userMessage 经过service封装的实体类
//     */
//    public void updataUser(UserMessage userMessage);

    /**
     * 判断用户是否在线
     * @return
     */
    public boolean isOnline(Long uid);

    /**
     * 登录服务
     * @param account
     * @param password
     * @return 非空表示成功，否则失败
     */
    public UserMessage login(String account, String password);

    /**
     * 用户登出
     * @param uid
     */
    public void logout(long uid);

    /**
     * 注册
     * @return
     */
    public String register(User user);

}
