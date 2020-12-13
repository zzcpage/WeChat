package service.impl;

import dao.MessageDao;
import dao.MsgListDao;
import dao.UserDao;
import domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.FriendService;
import service.MessageService;

import java.util.ArrayList;
import java.util.List;

@Service("messageService")
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageDao messageDao;

    @Autowired
    private MsgListDao msgListDao;

    @Autowired
    private FriendService friendService;

    @Autowired
    private UserDao userDao;


    @Override
    public void addMsg(Message m) {
        Long suid = m.getSuid();
        Long ruid = m.getRuid();
        //改变消息列表
        MsgList m1 = msgListDao.getList(suid, ruid);
        if(m1 != null) {
            m1.setDate(m.getDate());
            m1.setLastMessage(m.getMessage());
            msgListDao.updateMsgList(m1);
        } else {
            MsgList msgList = new MsgList(suid, ruid, m.getMessage(), m.getDate());
        }
        //改变消息列表
        MsgList m2 = msgListDao.getList(ruid, suid);
        if(m2 != null) {
            m2.setDate(m.getDate());
            m2.setLastMessage(m.getMessage());
            msgListDao.updateMsgList(m2);
        } else {
            MsgList msgList = new MsgList(ruid, suid, m.getMessage(), m.getDate());
        }
        messageDao.addMsg(m);
    }

    //更新未读数据
    private void UpdateMsg(Long mid) {
        messageDao.updataMsg(mid);
    }

    @Override
    public ArrayList<MessageDetail> listMessage(Long uid1, Long uid2, Integer n) {
        Integer unRead = messageDao.unReadCount(uid1, uid2);
        ArrayList<Message> messages;
        messages = messageDao.listMsg(uid1, uid2, n + unRead);
        ArrayList<MessageDetail> ret = new ArrayList<>();
        for(Message message : messages) {
            User user = userDao.getUserById(message.getSuid());
            ret.add(new MessageDetail(message, user.getUname(), user.getHeadImg()));
        }
        return ret;
    }

    @Override
    public ArrayList<MessageDetail> listUnReadMessage(Long uid1, Long uid2) {
        ArrayList<Message> messages = messageDao.listUnReadMsg(uid1, uid2);
        ArrayList<MessageDetail> ret = new ArrayList<>();
        for(Message message : messages) {
            UpdateMsg(message.getId());
            User user = userDao.getUserById(message.getSuid());
            ret.add(new MessageDetail(message, user.getUname(), user.getHeadImg()));
        }
        return ret;
    }

    @Override
    public Integer unReadMessageCount(Long uid1, Long uid2) {
        return messageDao.unReadCount(uid1, uid2);
    }




    private List<MessageDetail> toMsgDetail(List<Message> messages) {
        List<MessageDetail> ret = new ArrayList<>();
        for(Message message:messages) {
            User user = userDao.getUserById(message.getSuid());
            ret.add(new MessageDetail(message, user.getUname(), user.getHeadImg()));
        }
        return ret;
    }

    @Override
    public Page<MessageDetail> getPage(Long uid, Long friendUid, Integer pageNo, Integer pageSize) {
        Page<MessageDetail> ret = new Page<>();
        Integer total = messageDao.queryMessageCount(uid, friendUid);
        Integer pageTotal = total / pageSize;
        ret.setPageTotal(pageSize * (pageTotal) == total ? pageTotal : pageTotal + 1);
        ret.setTotal(total);
        ret.setPageNo(pageNo);
        ret.setPageSize(pageSize);
        List<Message> messages = messageDao.queryPageInfo(uid, friendUid, ret.getPageNo(), pageSize);
        ret.setItems(toMsgDetail(messages));
        return ret;
    }


}
