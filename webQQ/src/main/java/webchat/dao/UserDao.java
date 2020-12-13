package webchat.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import webchat.domain.User;

import java.util.ArrayList;
//交给spring容器管理
@Repository
public interface UserDao {
    // 注册用户加入数据库
    @Insert("insert into user(uname,account,password,birthday,sex,email,headimg,state,signature) values(#{uname},#{account},#{password},#{birthday},#{sex},#{email},#{headimg},#{state},#{signature})")
    boolean insertUser(User p)  ;
    // 获取用户 通过ID where uid=#{uid}
    @Select("select * from user ")
    ArrayList<User> getUserById(long uid) ;
    // 获取用户 通过账号 密码
    @Select("select * from user where account = #{userName} and password = #{password}")
    User getUser(@Param("userName") String account ,@Param("password") String password) ;
}
