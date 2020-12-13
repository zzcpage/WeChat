package webchat.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webchat.dao.MsgListDao;
import webchat.dao.RequestDao;
import webchat.domain.MsgList;
import webchat.service.MsgListService;

import java.util.ArrayList;
import java.util.Date;

@Service("msgListService")
public class MsgListServiceImpl implements MsgListService {
    @Autowired
    private MsgListDao msgListDao;
    @Override
    public void test() {
//        MsgList msgList = new MsgList() ;
//        msgList.setSuid(1);
//        msgList.setRuid(2);
//        msgList.setMsg("你是鸡儿");
//        msgList.setDate(new Date(2019-1900,4,2,12,25,30));
//        msgListDao.insertMsgList(msgList);
//        ArrayList<MsgList> p = new ArrayList<>() ;
//        p = msgListDao.listMsgList(1);
//        System.out.println(p);
//        MsgList q  ;
//        q = msgListDao.getMsgList(1,2);
//        System.out.println(q);

    }
}
