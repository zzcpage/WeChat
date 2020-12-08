package webqq.dao;

import webqq.domain.Group;
import webqq.domain.User;

import java.util.ArrayList;

public interface GroupDao {
    /**
     * select * from group where Uid = p.Uid group by pos ;
     * @param p 登录用户对象
     * @return  返回一个有序（下标对应位置）的分组列表
     */
    public ArrayList<Group> getGroup(User p) ;

    /**
     * 用户增加分组
     * @param p 增加分组的对象（注意位置要保证正确性，也就是位置直接取分组数目+1）
     */
    public void addGroup(User p ) ;

    /**
     * 用户跟新分组信息，一般是更新分组的名称
     * @param p  传入更新后的分组对象
     */
    public void updateGroup(User p) ;
    /**
     * 用户删除分组,删除分组后要维护好友关系的列表
     * 删除分组后，记得维护一下列表位置
     */
    public void deleteGroup(User p );
}
