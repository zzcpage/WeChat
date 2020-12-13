package webchat.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import webchat.domain.MsgList;

import java.util.ArrayList;

@Repository
public interface MsgListDao {
    //获取用户uid的消息列表
    @Select("select * from msglist where suid = #{uid}")
    ArrayList<MsgList> listMsgList(long uid);
    // 插入新的消息列表
    @Insert("insert into msglist(suid,ruid,date,msg) values(#{suid},#{ruid},#{date},#{msg}) ")
    void insertMsgList(MsgList p) ;
    //删除消息列表对象suid 为当前用户  ruid 为要删除的列表对象
    @Delete("delete from msglist where suid=#{id1} and ruid=#{id2}")
    void deleteMsgListById(@Param("id1") long suid,@Param("id2") long ruid) ;
    @Update("update msglist set date=#{date} and msg=#{msg}")
    void updateMsgList(MsgList p) ;
    @Delete("delete from msglist where id=#{id}")
    void deleteMsgList(MsgList p) ;
    @Select("select * from msglist where suid=#{suid} and ruid=#{ruid}")
    MsgList getMsgList(@Param("suid") long suid ,@Param("ruid") long ruid);
}
