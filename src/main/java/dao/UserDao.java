package dao;

import domain.User;
import domain.UserMessage;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
@Repository("userDao")
public interface UserDao {
    /**
     * 用于登录获取用户对象
     * @param account 账号名
     * @param password  密码
     * @return  从数据库查询到的用户对象
     */
    //public User getUser(String account , String password) ;
    // 获取用户 通过账号 密码
    @Select("select * from user where account = #{userName} and password = #{password}")
    User getUser(@Param("userName") String account , @Param("password") String password) ;

    /**
     * 根据uid查找用户
     * @param uid 用户唯一标识
     * @return
     */
    public User getUserById(Long uid);
    // 获取用户 通过ID where uid=#{uid}
    @Select("select * from user where uid=#{uid}")
    User getUserById(long uid) ;

    @Select("select * from user where account=#{account}")
    public User getUserByAccount(String account);
    /**
     * 更新用户信息，主要用于注册
     * @param p 传入用户对象进行新建用户
     * @return  注册的结果
     */
    @Insert("insert into user(uname,account,password,birthday,sex,email,headimg,state,signature) values(#{uname},#{account},#{password},#{birthday},#{sex},#{email},#{headimg},#{state},#{signature})")
    boolean insertUser(User p)  ;
    //public boolean insertUser(User p ) ;

    /**
     * 用于更新用户的资料信息
     * @param p 用户对象
     * @return  更新的结果
     */
    @Update("update user set uname=#{uname},password=#{password},birthday=#{birthday},sex=#{sex},headimg=#{headimg},state=#{state},signature=#{signature} where uid=#{uid}; \n" +
            "select last_insert_id() ;")
    boolean updateUser(User p ) ;

    @Update("update user set state = #{state} where uid = #{uid}")
    void updateUserState(@Param("uid") Long uid, @Param("state")Integer state);
}
