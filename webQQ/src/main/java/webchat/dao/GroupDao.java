package webchat.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import webchat.domain.Group;

import java.util.ArrayList;
@Repository
public interface GroupDao {
    @Insert("insert into group(uid,gname)values(#{uid},#{gname})")
    void insertGroup(Group p) ;
    @Update("update group set gname=#{gname} where id=#{id}")
    void updateGroup(Group p ) ;
    @Select("select * from group where uid=#{uid}")
    ArrayList<Group> listGroup(long uid)  ;
    @Delete("delete from group where id=#{id}")
    void deleteGroupById(long id) ;
    @Delete("delete from group where id=#{id}")
    void deleteGroup(Group p) ;
}
