package webchat.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import webchat.domain.Message;

import java.util.ArrayList;

@Repository
public interface MessageDao {
    //查询用户1 和用户2的聊天记录 按时间排序 的 start-end条记录(0开始)
    @Select("select * from message where (suid=#{uid1} and ruid = #{uid2}) or (suid = #{uid2} and ruid = #{uid1}) order by date desc limit #{start},#{end}")
    ArrayList<Message> listMessage(@Param("uid1") long uid1 ,@Param("uid2") long uid2,@Param("start") long start,@Param("end") long end) ;
    @Insert("insert into message(suid,ruid,date,msg,state) values(#{suid},#{ruid},#{date},#{msg},#{state})")
    void insertMessage(Message p);
    //更新状态信息
    @Update("update message set state= 1 where id = #{id}")
    void updateMessageState(Message p )  ;
    //更新状态信息 uid1是发送者  uid2 是接收者
    @Update("update message set state= 1 where suid=#{uid1} and ruid = #{uid2}")
    void updateMessage(@Param("uid1") long uid1,@Param("uid2") long uid2 )  ;
    //获取用户为读消息列表
    @Select("select * from message where state = 0 and ruid=#{uid}")
    //获取用户A和用户B的聊天记录条数
    ArrayList<Message> listUnRead(long uid) ;
    @Select("select count(id) from message where (suid=#{uid1} and ruid = #{uid2}) or (suid = #{uid2} and ruid = #{uid1})")
    long getCountMessage(@Param("uid1") long uid1,@Param("uid2") long uid2);
    @Select("select * from message where (suid=#{uid1} and ruid = #{uid2}) or (suid = #{uid2} and ruid = #{uid1}) order by date desc limit (#{curPage}-1)*#{pageSize},#{pageSize}")
    ArrayList<Message> listMessagePage(@Param("uid1") long uid1 ,@Param("uid2") long uid2,@Param("curPage") long curPage,@Param("pageSize") long pageSize);
}
