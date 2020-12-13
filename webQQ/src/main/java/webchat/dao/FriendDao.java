package webchat.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import webchat.domain.Friend;
import webchat.domain.User;

import java.util.ArrayList;

@Repository
public interface FriendDao {
    @Insert("insert into friend(uid1,uid2,group1,group2) values(#{uid1},#{uid2},#{group1},#{group2})")
    void insertFriend(Friend p );
    @Delete("delete from friend where uid1=#{uid1} and uid2=#{uid2}")
    void deleteFriend(Friend p) ;
    @Delete("delete from friend where (uid1=#{uid1} and uid2=#{uid2}) or (uid1=#{uid2} and uid2=#{uid1})")
    void deleteFriendById(@Param("uid1") long uid1,@Param("uid2") long uid2) ;
    @Select("select * from friend where uid1 = #{uid1} ")
    ArrayList<Friend> listFriend(User p );
    @Select("select * from friend where uid1 = #{uid1} ")
    ArrayList<Friend> listFriendById(@Param("uid1") long uid );
    @Update("update friend set group1 = #{group1} and group2 = #{group2} where uid1 = #{uid1} and uid2=#{uid2}")
    void updateFriend(Friend p );
    @Select("select * from friend where uid1=#{uid1} and uid2=#{uid2}")
    Friend getFriend(@Param("uid1") long uid1 ,@Param("uid2") long uid2) ;
}
