package webqq.dao;

import webqq.domain.User;

/**
 * 用户的Dao
 */
public interface UserDao {
    /**
     * 用于登录获取用户对象
     * @param account 账号名
     * @param password  密码
     * @return  从数据库查询到的用户对象
     */
    public User getUser(String account , String password) ;

    /**
     * 根据uid查找用户
     * @param uid 用户唯一标识
     * @return
     */
    public User getUserById(int uid);
    /**
     * 更新用户信息，主要用于注册
     * @param p 传入用户对象进行新建用户
     * @return  注册的结果
     */
    public boolean insertUser(User p ) ;

    /**
     * 用于更新用户的资料信息
     * @param p 用户对象
     * @return  更新的结果
     */
    public boolean updateUser(User p ) ;

    /**
     * 用于查询指定用户的在线状态
     * @param uid 用户的唯一标识
     * @return  在线状态
     */
    public boolean isOnLine(int uid)    ;
}
