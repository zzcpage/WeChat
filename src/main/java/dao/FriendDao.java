package dao;

import domain.Friend;
import domain.Group;
import domain.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface FriendDao {


    @Delete("delete from friend where uid1=#{uid1} and uid2=#{uid2}")
    void deleteFriend(Friend p) ;
    @Select("select * from friend where uid1 = #{uid1} ")
    ArrayList<Friend> listFriend(User p);
    @Select("select * from friend where uid1 = #{uid1} ")
    ArrayList<Friend> listFriendById(@Param("uid1") long uid );

//    /**
//     *
//     * @param p 当前用户对象
//     * @return 返回属于p的所有好友，根据id2查询到好友 group1查询到好友在id1中的分组
//     */
//    public ArrayList<Friend> listFriend(User p);




    /**
     * delete from friends where (id1=uid1 and id2 = uid2) or (id1 = uid2 and id2 = uid1)
     * 删除双方的好友
     */
    @Delete("delete from friend where (uid1=#{uid1} and uid2=#{uid2}) or (uid1=#{uid2} and uid2=#{uid1})")
    void deleteFriendById(@Param("uid1") long uid1, @Param("uid2") long uid2) ;


    /**
     * 删除分组的所有好友
     * @param gid 分组id
     */
    @Delete("delete from friend where group2=#{gid} or group1=#{gid}")
    public void deleteGroupFriends(Long gid);



    /**
     * 需要事先确认分组序号是否存在(防止在未同意后删除了用户分组导致失败，如果删除了分组就分配到默认分组)
     * 新增两条记录.记录信息包括用户1和用户2 的id 以及 用户1在用户2的分组pos 以及用户2在用户1的分组pos
     */
    @Insert("insert into friend(uid1,uid2,group1,group2) values(#{uid1},#{uid2},#{group1},#{group2})")
    public void insertFriend(Friend friend);

    /**
     * 更新好友关系，当好友所在分组发生变化时候，进行更新分组信息
     */
    @Update("update friend set group1 = #{group1} , group2 = #{group2} where uid1 = #{uid1} and uid2=#{uid2}")
    public void updateFriend(Friend friend);

    /**
     * 通过分组id获取该分组下所有好友
     * @param gid 分组id
     * @return
     */
    @Select("select * from friend where group2 = #{gid} ")
    public List<Friend> getFriendsByGId(Long gid);


    @Select("select * from friend where (uid1=#{uid1} and uid2=#{uid2}) or(uid1=#{uid2} and uid2=#{uid1})")
    public Friend getFriend(@Param("uid1") Long uid1, @Param("uid2") Long uid2);
}
