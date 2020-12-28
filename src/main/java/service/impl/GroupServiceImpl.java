package service.impl;

import dao.*;
import domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import service.GroupService;

import java.util.ArrayList;
import java.util.List;

@Service("groupService")
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupDao groupDao;

    @Autowired
    private FriendDao friendDao;

    @Autowired
    private MessageDao messageDao;

    @Autowired
    @Qualifier("userDao")
    private UserDao userDao;

    //Friend List变为 FriendMessage List
    private List<FriendMessage> toFriendMsg(List<Friend> list, Long currentUid) {
        List<FriendMessage> ret = new ArrayList<>();
        for (Friend friend:list) {
            //自己是1，好友是2
            if(friend.getUid1() == currentUid) {
                User user = userDao.getUserById(friend.getUid2());
                ret.add(new FriendMessage(user));
            } else {
                User user = userDao.getUserById(friend.getUid1());
                ret.add(new FriendMessage(user));
            }
        }
        return ret;
    }

//    @Override
//    public ArrayList<GroupMessage> getGroups(User p) {
//        User user = userDao.getUserById(p.getUid());
//        ArrayList<GroupMessage> groupMessages = new ArrayList<>();
//        ArrayList<Group> groups = groupDao.getGroups(user);
//        for(Group g : groups) {
//            Long id = g.getId();
//            groupMessages.add(getGroupByGID(id));
//        }
//        return groupMessages;
//    }

    @Override
    public ArrayList<GroupMessage> getGroups(Long uid) {
        ArrayList<GroupMessage> groupMessages = new ArrayList<>();
        ArrayList<Group> groups = groupDao.listGroup(uid);
        for(Group g : groups) {
            Long id = g.getId();
            groupMessages.add(getGroupByGID(id));
        }
        return groupMessages;
    }

    @Override
    public GroupMessage getGroupByGID(Long gid) {
        Group group = groupDao.getGroupByGID(gid);
        List<Friend> friends = friendDao.getFriendsByGId(gid);
        List<FriendMessage> friendMessages = toFriendMsg(friends, group.getUid());
        GroupMessage ret = new GroupMessage(group, friendMessages);
        return ret;
    }

    @Override
    public Group getGroup(Long gid) {
        return groupDao.getGroupByGID(gid);
    }

    @Override
    public List<Group> getAllGroup(Long gid) {
        return groupDao.listGroup(gid);
    }

    @Override
    public void updateGroup(Group p) {
        groupDao.updateGroup(p);
    }

    @Override
    public void addGroup(Group p) {
        groupDao.insertGroup(p);
    }



    @Override
    public void deleteGroup(Long gid) {
        //getGroupByGID(gid);
        friendDao.deleteGroupFriends(gid);
        groupDao.deleteGroupById(gid);
    }
}
