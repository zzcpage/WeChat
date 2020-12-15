package dao;

import domain.Request;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public interface RequestDao {


    //获取发送的请求消息
    @Select("select * from request where suid=#{uid}")
    ArrayList<Request> listSendRequest(long uid) ;
    //获取发送和接收到的请求消息
    @Select("select * from request where suid=#{uid} or ruid=#{uid}")
    ArrayList<Request> listAllRequest(long uid) ;


//    @Select("select * from request where suid=#{id1} and ruid=#{id2} ")
//    Request getRequest(@Param("id1") long id1 , @Param("id2") long id2) ;

    //uid2发给uid1的
    @Select("select * from request where (suid=#{uid2} and ruid=#{uid1} and state = 0)")
    Request getRequest(@Param("uid1") Long uid1,@Param("uid2") Long uid2);

    //public List<Request> getRequest(Long uid);
    //获取接收到的请求信息
    @Select("select * from request where ruid=#{uid}")
    ArrayList<Request> listRequest(long uid) ;

    //public Request getRequest(Long uid1, Long uid2);


    //public void updateRequest(Request request);
    @Update("update request set state = #{state}, sgroup = #{sgroup} where id = #{id}")
    void updateRequest(Request p) ;

    //public void addRequest(Request request);
    @Insert("insert into request(suid,ruid,date,remark,state,rgroup) values(#{suid},#{ruid},#{date},#{remark},#{state},#{rgroup})")
    void insertRequest(Request p) ;
}
