package service;

import domain.Group;
import domain.GroupMessage;
import domain.User;

import java.util.ArrayList;

public interface GroupService {

    /**获得该用户好友列表
     * @param p 登录用户对象
     * @return  返回该用户的所有分组
     */
    public ArrayList<GroupMessage> getGroups(User p);


    /**获得该用户好友列表
     * @param p 登录用户uid
     * @return  返回该用户的所有分组
     */
    public ArrayList<GroupMessage> getGroups(Long uid);

    /**获得一个指定的好友分组
     * @param gid 分组id
     * @return  返回一个好友分组
     */
    public GroupMessage getGroupByGID(Long gid);

    /**
     * 用户更新分组信息，一般是更新分组的名称
     * @param p  传入更新后的分组对象
     */
    public void updateGroup(Group p);

    /**
     * 用户增加分组
     * @param p 增加分组的对象
     */
    public void addGroup(Group p);

    /**
     * 删除分组
     * @param g 分组
     */
    public void deleteGroup(Group g);

    /**
     * 删除分组
     * @param gid 分组id
     */
    public void deleteGroup(Long gid);

}
