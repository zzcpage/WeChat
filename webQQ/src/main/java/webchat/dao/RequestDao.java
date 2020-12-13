package webchat.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import webchat.domain.Request;

import java.util.ArrayList;

@Repository
public interface RequestDao {
    //获取接收到的请求信息
    @Select("select * from request where ruid=#{uid}")
    ArrayList<Request>  listRequest(long uid) ;
    //获取发送的请求消息
    @Select("select * from request where suid=#{uid}")
    ArrayList<Request> listSendRequest(long uid) ;
    //获取发送和接收到的请求消息
    @Select("select * from request where suid=#{uid} or ruid=#{uid}")
    ArrayList<Request> listAllRequest(long uid) ;
    @Insert("insert into request(suid,ruid,date,remark,state,rgroup) values(#{suid},#{ruid},#{date},#{remark},#{state},#{rgroup})")
    void insertRequest(Request p) ;
    @Update("update request set state = #{state} where id = #{id}")
    void updateRequest(Request p) ;
    @Select("select * from request where suid=#{id1} and ruid=#{id2} and state = 0")
    Request getRequest(@Param("id1") long id1 ,@Param("id2") long id2) ;
}
