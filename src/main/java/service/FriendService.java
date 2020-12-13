package service;

import domain.FriendMessage;

public interface FriendService {

    /**
     * 修改好友所在分组
     * @param uid 当前用户id
     * @param Frienduid 好友id
     * @param newGid 新分组id（属于当前用户的分组）
     */
    public void updataFriendInfo(Long uid, Long Frienduid, Long newGid);

    /**
     * 通过好友id删除好友
     * @param uid 自己的uid
     * @param friendUid 好友的uid
     */
    public void deleteFriendById(Long uid, Long friendUid);

    /**
     * 添加好友
     * @param uid 用户uid
     * @param friendUid 好友uid
     * @param gid1  用户在好友的组别
     * @param gid2  好友在用户的组别
     */
    public void addFriend(Long uid, Long friendUid, Long gid1, Long gid2);


    /**
     * 通过用户和好友的uid 来获得好友的信息
     * @param uid 用户uid
     * @param friendUid 好友uid
     * @return 无好友关系返回空 否则返回FriendMessage对象
     */
    public FriendMessage getFriendById(Long uid, Long friendUid);

}
