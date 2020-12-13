package webchat.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import webchat.domain.Impress;
import webchat.domain.Message;

import java.util.ArrayList;

@Repository
public interface ImpressDao {
    //获取用户1 的所有印象 从 start-end 条记录
    @Select("select * from impress where ruid=#{uid} order by date desc limit #{start},#{end}")
    ArrayList<Impress> listImpress(@Param("uid") long uid,@Param("start") long start,@Param("end") long end) ;
   //更新印象
    @Update("update impress set date=#{date},msg=#{msg} where suid=#{suid} and ruid=#{ruid}")
    void updateImpress(Impress p) ;
    //增加印象
    @Insert("insert into impress(suid,ruid,date,msg) values(#{suid},#{ruid},#{date},#{msg}) ")
    void insertImpress(Impress p);
    @Delete("delete impress where suid=#{suid} and ruid=#{ruid}")
    void deleteImpress(Impress p) ;
    @Select("select count(id) from impress where (suid=#{uid1} and ruid = #{uid2}) or (suid = #{uid2} and ruid = #{uid1})")
    long getCountMessage(@Param("uid1") long uid1,@Param("uid2") long uid2);
    @Select("SET @a=CONCAT('select * from impress (suid=',#{uid1},' and ruid ' , #{uid2} , ' ) or (suid = ', #{uid2} , 'and ruid =', #{uid1},') order by date desc limit ',(#{curPage}-1)*#{pageSize},',',#{pageSize});\n" +
            " PREPARE text FROM @a;\n" +
            " EXECUTE text;")
    ArrayList<Message> listMessagePage(@Param("uid1") long uid1 ,@Param("uid2") long uid2,@Param("curPage") long curPage,@Param("pageSize") long pageSize);

}
