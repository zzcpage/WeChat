package webchat.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webchat.dao.MessageDao;
import webchat.dao.MsgListDao;
import webchat.domain.Message;
import webchat.service.MessageService;

import java.util.ArrayList;
import java.util.Date;

@Service("messageService")
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageDao messageDao;
    @Override
    public void test() {
        Message p  ;
//        p.setDate(new Date(2019-1900,4,6,12,30,30));
//        p.setRuid(1);
//        p.setSuid(2);
//        p.setState(0);
//        p.setMsg("你是鸡儿");
//        messageDao.insertMessage(p);
        ArrayList<Message> q = new ArrayList<>() ;
        q = messageDao.listUnRead(1);
        System.out.println(q);
        q = messageDao.listMessage(1,2,1,2);
        System.out.println(q);
        long counts = messageDao.getCountMessage(1,2) ;
        System.out.println(counts);
//        System.out.println(q);
//        p = q.get(0) ;
//        messageDao.updateMessageState(p);
//        p = q.get(1);
//        messageDao.updateMessage(p.getSuid(),p.getRuid());

    }
}
