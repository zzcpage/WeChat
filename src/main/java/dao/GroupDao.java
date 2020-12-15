package dao;

import domain.Group;
import domain.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
@Repository
public interface GroupDao {



    /**
     * select * from group where Uid = p.Uid group by pos ;
     * @return  返回一个有序（下标对应位置）的分组列表
     */
    //public ArrayList<Group> getGroups(Long uid);
        @Select("select * from `group` where uid=#{uid}")
    ArrayList<Group> listGroup(long uid);

    /**
     * select * from group where Uid = p.Uid group by pos ;
     * @return  返回一个有序（下标对应位置）的分组列表
     */
    @Select("select * from `group` where id=#{gid}")
    Group getGroupByGID(Long gid);


    /**
     * 用户增加分组
     * @param p 增加分组的对象（注意位置要保证正确性，也就是位置直接取分组数目+1）
     */
    //public void addGroup(Group p);
    @Insert("insert into `group`(uid,gname)values(#{uid},#{gname})")
    void insertGroup(Group p);

    /**
     * 用户跟新分组信息，一般是更新分组的名称
     * @param p  传入更新后的分组对象
     */
    //public void updateGroup(Group p);
    @Update("update `group` set gname=#{gname} where id=#{id}")
    void updateGroup(Group p);

    /**
     * 用户删除分组,删除分组后要维护好友关系的列表
     * 删除分组后，记得维护一下列表位置
     */
    //public void deleteGroup(Group p);
    @Delete("delete from `group` where id=#{id}")
    void deleteGroup(Group p);

    @Delete("delete from `group` where id=#{id}")
    void deleteGroupById(long id);

}
