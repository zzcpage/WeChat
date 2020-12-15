package dao;

import domain.MsgList;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
@Repository
public interface MsgListDao {


    // 插入新的消息列表

    //删除消息列表对象suid 为当前用户  ruid 为要删除的列表对象
    @Delete("delete from msglist where suid=#{id1} and ruid=#{id2}")
    void deleteMsgListById(@Param("id1") long suid, @Param("id2") long ruid) ;





    //public ArrayList<MsgList> getListByUid(Long uid);
    //获取用户uid的消息列表
    @Select("select * from msglist where suid = #{uid}")
    ArrayList<MsgList> listMsgList(long uid);


    //public MsgList getList(Long uid, Long friendUid);
    @Select("select * from msglist where suid=#{suid} and ruid=#{ruid}")
    MsgList getMsgList(@Param("suid") long suid ,@Param("ruid") long ruid);

    //public void updateMsgList(MsgList msgList);
    @Update("update msglist set date=#{date} , msg=#{msg} where id=#{id}")
    void updateMsgList(MsgList p) ;

    //public void addMsgList(MsgList msgList);
    @Insert("insert into msglist(suid,ruid,date,msg) values(#{suid},#{ruid},#{date},#{msg}) ")
    void insertMsgList(MsgList p) ;

    @Delete("delete from msglist where id=#{listId}")
    void deleteMsgList(Long listId);

}
