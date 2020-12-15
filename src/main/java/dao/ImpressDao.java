package dao;

import domain.Impress;
import domain.Message;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public interface ImpressDao {
    //获取用户1 的所有印象 从 start-end 条记录
    //更新印象

    //增加印象


    @Select("SET @a=CONCAT('select * from impress (suid=',#{uid1},' and ruid ' , #{uid2} , ' ) or (suid = ', #{uid2} , 'and ruid =', #{uid1},') order by date desc limit ',(#{curPage}-1)*#{pageSize},',',#{pageSize});\n" +
            " PREPARE text FROM @a;\n" +
            " EXECUTE text;")
    ArrayList<Message> listMessagePage(@Param("uid1") long uid1 , @Param("uid2") long uid2, @Param("curPage") long curPage, @Param("pageSize") long pageSize);




    //public void addImpress(Impress impress);
    @Insert("insert into impress(suid,ruid,date,msg) values(#{suid},#{ruid},#{date},#{msg}) ")
    void insertImpress(Impress p);

    //public void updateImpress(Impress impress);
    @Update("update impress set date=#{date},msg=#{msg} where suid=#{suid} and ruid=#{ruid}")
    void updateImpress(Impress p) ;

    @Delete("delete from impress where id = #{impressId}")
    void deleteImpress(Long impressId);



    @Select("select count(id) from impress where (suid=#{uid1} and ruid = #{uid2}) or (suid = #{uid2} and ruid = #{uid1})")
    long getCountMessage(@Param("uid1") long uid1,@Param("uid2") long uid2);

    @Select("select count(id) from impress where ruid = #{uid1}")
    long getCountImpress(@Param("uid1") long uid1);

//    @Select("select count(id) from impress where (suid=#{uid1} and ruid = #{uid2}) or (suid = #{uid2} and ruid = #{uid1})")
//    long getCountMessage(@Param("uid1") long uid1,@Param("uid2") long uid2);


    @Select("select * from impress where ruid=#{uid} order by date desc limit #{start},#{end}")
    ArrayList<Impress> listImpress(@Param("uid") long uid, @Param("start") long start, @Param("end") long end) ;

    //uid2对did1的印象
    @Select("select * from impress where suid=#{uid2} and ruid=#{uid1}")
    Impress getImpress(@Param("uid1") Long uid1,@Param("uid2") Long uid2);
}
