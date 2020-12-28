package service.impl;

import dao.MsgListDao;
import domain.MsgListDetail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.MsgListService;

import java.util.ArrayList;

import static org.junit.Assert.*;
@ContextConfiguration("classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class MsgListServiceImplTest {

    @Autowired
    private MsgListService msgListService;

    @Test
    public void getMessageListByUid() {
        //ArrayList<MsgListDetail> messageListByUid = msgListService.getMessageListByUid(15l);
        //System.out.println(messageListByUid);
    }

    @Test
    public void addMsgList() {
    }

    @Test
    public void deleteMsgList() {
        msgListService.deleteMsgList(5l);
    }

    @Test
    public void updateMsgList() {

    }
}