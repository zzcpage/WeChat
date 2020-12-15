package service.impl;


import dao.FriendDao;
import dao.GroupDao;
import dao.UserDao;
import domain.Friend;
import domain.FriendMessage;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import service.FriendService;
@Service("friendService")
public class FriendServiceImpl implements FriendService {

    @Autowired
    private FriendDao friendDao;

    @Autowired
    @Qualifier("userDao")
    private UserDao userDao;

    @Autowired
    private GroupDao groupDao;

    @Override
    public void updataFriendInfo(Long uid, Long friendUid, Long newGid) {
        Friend friend = friendDao.getFriend(uid, friendUid);
        if(uid.equals(friend.getUid1())) {
            friend.setGroup2(newGid);
        } else {
            friend.setGroup1(newGid);
        }
        friendDao.updateFriend(friend);
    }

    @Override
    public void deleteFriendById(Long uid, Long friendUid) {
        friendDao.deleteFriendById(uid, friendUid);
    }

    @Override
    public void addFriend(Long uid, Long friendUid, Long gid1, Long gid2) {
        Friend friend = new Friend(uid, friendUid, gid1, gid2);
        friendDao.insertFriend(friend);
    }

    @Override
    public FriendMessage getFriendById(Long uid, Long friendUid) {
        User user = userDao.getUserById(friendUid);
        FriendMessage friendMessage = new FriendMessage(user);
        return friendMessage;
    }


}
