package service.impl;

import dao.MessageDao;
import dao.MsgListDao;
import dao.UserDao;
import domain.FriendMessage;
import domain.MsgList;
import domain.MsgListDetail;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import service.FriendService;
import service.MsgListService;

import java.util.ArrayList;

@Service("msgListService")
public class MsgListServiceImpl implements MsgListService {

    @Autowired
    private MsgListDao msgListDao;

    @Autowired
    private FriendService friendService;

    @Autowired
    private MessageDao messageDao;

    @Autowired
    @Qualifier("userDao")
    private UserDao userDao;

    @Override
    public ArrayList<MsgListDetail> getMsgList(Long uid) {
        ArrayList<MsgList> temp = msgListDao.listMsgList(uid)  ;
        ArrayList<MsgListDetail> p =  new ArrayList<>() ;
        int count  ;
        for(MsgList msgList:temp)
        {
            User user = userDao.getUserById(msgList.getRuid());
            FriendMessage friendMessage  = new FriendMessage(user) ;
            count = messageDao.unReadCount(user.getUid(),uid);
            MsgListDetail msgListDetail = new MsgListDetail(msgList,friendMessage,count);
            p.add(msgListDetail) ;
        }
        System.out.println("用户: "+uid+"的消息列表");
        System.out.println(p);
        return p;
    }

    @Override
    public void addMsgList(MsgList msgList) {
        msgListDao.insertMsgList(msgList);
    }

    @Override
    public void deleteMsgList(Long listId) {
        msgListDao.deleteMsgList(listId);
    }

    @Override
    public void updateMsgList(MsgList msgList) {
        msgListDao.updateMsgList(msgList);
    }

    @Override
    public MsgList getMsg(Long uid1, Long uid2) {
        return msgListDao.getMsgList(uid1, uid2);
    }
}
