package service.impl;

import dao.MessageDao;
import dao.MsgListDao;
import dao.UserDao;
import domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    @Qualifier("userDao")
    private UserDao userDao;


    @Override
    public void addMsg(Message m) {
        Long suid = m.getSuid();
        Long ruid = m.getRuid();
        //改变消息列表
        MsgList m1 = msgListDao.getMsgList(suid, ruid);
        if(m1 != null) {
            m1.setDate(m.getDate());
            m1.setMsg(m.getMsg());
            msgListDao.updateMsgList(m1);
        } else {
            MsgList msgList = new MsgList(suid, ruid, m.getMsg(), m.getDate());
            msgListDao.insertMsgList(msgList);
        }
        //改变消息列表
        MsgList m2 = msgListDao.getMsgList(ruid, suid);
        if(m2 != null) {
            m2.setDate(m.getDate());
            m2.setMsg(m.getMsg());
            msgListDao.updateMsgList(m2);
        } else {
            MsgList msgList = new MsgList(ruid, suid, m.getMsg(), m.getDate());
            msgListDao.insertMsgList(msgList);
        }
        messageDao.insertMessage(m);
    }

    //更新未读数据
    private void UpdateMsg(Long mid) {
        messageDao.updataMsg(mid);
    }

    private void UpdateMsg(Long uid1, Long uid2) {
        messageDao.updateMessage(uid1, uid2);
    }

    @Override
    public void addMessage(Message message) {
        messageDao.insertMessage(message);
    }

    @Override
    public ArrayList<MessageDetail> listMessage(Long uid1, Long uid2, Integer n) {
        Integer unRead = messageDao.unReadCount(uid2, uid1);
        System.out.println(unRead);
        ArrayList<Message> messages;
        messages = messageDao.listMessage(uid1, uid2,0 , n + unRead);
        ArrayList<MessageDetail> ret = new ArrayList<>();
        //更新消息状态
        UpdateMsg(uid2, uid1);
        for(Message message : messages) {
            User user = userDao.getUserById(message.getSuid());
            ret.add(new MessageDetail(message, user.getUname(), user.getHeadimg()));
        }
        return ret;
    }

    @Override
    public ArrayList<MessageDetail> listMessage(Long uid1, Long uid2, Long start, Integer n) {
        ArrayList<Message> messages;
        messages = messageDao.listMessage(uid1, uid2, start ,n);
        ArrayList<MessageDetail> ret = new ArrayList<>();
        //更新消息状态
        //UpdateMsg(uid2, uid1);
        for(Message message : messages) {
            User user = userDao.getUserById(message.getSuid());
            ret.add(new MessageDetail(message, user.getUname(), user.getHeadimg()));
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
            ret.add(new MessageDetail(message, user.getUname(), user.getHeadimg()));
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
            ret.add(new MessageDetail(message, user.getUname(), user.getHeadimg()));
        }
        return ret;
    }

    @Override
    public Page<MessageDetail> getPage(Long uid, Long friendUid, Long pageNo, Long pageSize) {
        Page<MessageDetail> ret = new Page<>();
        Long total = messageDao.getCountMessage(uid, friendUid);
        Long pageTotal = total / pageSize;
        ret.setPageTotal(pageSize * (pageTotal) == total ? pageTotal : pageTotal + 1);
        ret.setTotal(total);
        ret.setPageNo(pageNo);
        ret.setPageSize(pageSize);
        List<Message> messages = messageDao.listMessage(uid, friendUid, (ret.getPageNo() - 1) * pageNo, pageSize);
        ret.setItems(toMsgDetail(messages));
        return ret;
    }


    @Override
    public void updateStatue(Long uid1, ArrayList<Message> p) {
        for(Message message :p)
        {
            if(message.getSuid()!=uid1)
                messageDao.updateMessageState(message);
        }
    }

    @Override
    public void updateStatue(Long id) {
        messageDao.updataMsg(id);
    }

}
