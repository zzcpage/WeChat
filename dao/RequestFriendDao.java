package webqq.dao;

import webqq.domain.RequestFriend;
import webqq.domain.User;

import java.util.ArrayList;

public interface RequestFriendDao {
    /**
     * 用于获取用户获取到的好友请求,可以限制获取的数量
     * @param p 用户1
     * @return  收到的请求列表
     */
    public ArrayList<RequestFriend> listRequest(User p );

    /**
     * 增加好友请求
     * @param p 好友请求对象p ,需要事先检查在好友结果表中有没有正在等待验证的由 uid1发送给 uid2的好友验证，如果有则不进行好友申请
     */
    public void addRequest(RequestFriend p);

}
