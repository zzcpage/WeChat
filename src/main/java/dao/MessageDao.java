package dao;

import domain.Message;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public interface MessageDao {


    //更新状态信息 uid1是发送者  uid2 是接收者
    @Update("update message set state= 1 where suid=#{uid1} and ruid = #{uid2}")
    void updateMessage(@Param("uid1") long uid1,@Param("uid2") long uid2 )  ;
    //获取用户为读消息列表
    @Select("select * from message where (suid=#{uid1} and ruid = #{uid2}) or (suid = #{uid2} and ruid = #{uid1}) order by date desc limit (#{curPage}-1)*#{pageSize},#{pageSize}")
    ArrayList<Message> listMessagePage(@Param("uid1") long uid1 ,@Param("uid2") long uid2,@Param("curPage") long curPage,@Param("pageSize") long pageSize);



    /**
     * 用于加入消息记录到数据库
     * @param p
     */
    //public void addMsg(Message p);
    @Insert("insert into message(suid,ruid,date,msg,state) values(#{suid},#{ruid},#{date},#{msg},#{state})")
    void insertMessage(Message p);


    /**
     * 更新消息（是否已读）
     * @param mid messageid
     */
    @Update("update message set state=1 where id=#{mid}")
    public void updataMsg(Long mid);

    //更新状态信息
    @Update("update message set state= 1 where id = #{id}")
    void updateMessageState(Message p );

    /**
     * 查询数据库中 双方的聊天记录 uid1发送给uid2 和 uid2 发送给 uid1的数据
     * select * from msg where (id1=uid1 and id2 = uid2) or (id1 = uid2 and id2 = uid1) order by dates desc limit 0,n ;
     * @param uid1  当前登录的用户uid
     * @param uid2  登录用户所聊天对象的uid
     * @return
     */
    //public ArrayList<Message> listMsg(Long uid1, Long uid2, int n);
    @Select("select * from message where (suid=#{uid1} and ruid = #{uid2}) or (suid = #{uid2} and ruid = #{uid1}) order by date desc limit #{start},#{end}")
    ArrayList<Message> listMessage(@Param("uid1") long uid1 , @Param("uid2") long uid2, @Param("start") long start, @Param("end") long end) ;



    /**
     * 查询数据库中 双方的聊天记录 uid1发送给uid2 的未读数据
     * @return
     */
    @Select("select * from message where suid=#{uid1} and ruid=#{uid2} and state=0 ")
    ArrayList<Message> listUnReadMsg(@Param("uid1") Long uid1, @Param("uid2")Long uid2);
//    @Select("select * from message where state = 0 and ruid=#{uid}")
//    //获取用户A和用户B的聊天记录条数
//    ArrayList<Message> listUnRead(long uid);


    /**
     * 查询数据库中 双方的聊天记录 uid1发送给uid2 的未读数据的条数
     * @param uid1  当前登录的用户uid
     * @param uid2  登录用户所聊天对象的uid
     * @return
     */
    @Select("select count(id) from message where state = 0 and suid=#{uid1} and ruid=#{uid2} ")
    Integer unReadCount(@Param("uid1") Long uid1,@Param("uid2") Long uid2);

    /**
     *
     * @return
     */
    //public Integer queryMessageCount(Long uid1, Long uid2);
    @Select("select count(id) from message where (suid=#{uid1} and ruid = #{uid2}) or (suid = #{uid2} and ruid = #{uid1})")
    long getCountMessage(@Param("uid1") long uid1,@Param("uid2") long uid2);


    /**
     * 获取分页后的数据并包装为page类返回
     * @param uid 用户uid
     * @param friendUid 好友uid
     * @param pageNo 当前页码
     * @param pageSize 每一页包含的消息
     * @return
     */
    //public List<Message> queryPageInfo(Long uid, Long friendUid, Long pageNo, Long pageSize);

}
