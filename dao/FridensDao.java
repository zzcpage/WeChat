package webqq.dao;
import webqq.domain.Friends;
import webqq.domain.User;

import java.util.ArrayList;

public interface FridensDao {
    /**
     *
     * @param p 当前用户对象
     * @return 返回属于p的所有好友，根据id2查询到好友 group1查询到好友在id1中的分组
     */
    public ArrayList<Friends> listFriend(User p);

    /**
     * delete from friends where (id1=uid1 and id2 = uid2) or (id1 = uid2 and id2 = uid1)
     * 删除双方的好友
     * @param p 当前用户对象
     * @param p2 被删除对象
     */
    public void deleteFriends(User p ,User p2);

    /**
     * 需要事先确认分组序号是否存在(防止在未同意后删除了用户分组导致失败，如果删除了分组就分配到默认分组)
     * 新增两条记录.记录信息包括用户1和用户2 的id 以及 用户1在用户2的分组pos 以及用户2在用户1的分组pos
     * @param p     用户1
     * @param p2    用户2
     */
    public void insertFriends(User p , User p2,int pos1 , int pos2);

    /**
     * 更新好友关系，当好友所在分组发生变化时候，进行更新分组信息
     * @param p             用户1
     * @param p2            用户2
     * @param pos1          用户2在用户1 的分组
     * @param pos2          用户1在用户2的分组
     */
    public void updateFriend(User p , User p2 , int pos1 , int pos2) ;
}
