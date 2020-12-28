package service.impl;

import dao.MessageDao;
import domain.Message;
import domain.MessageDetail;
import domain.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.MessageService;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.*;
@ContextConfiguration("classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class MessageServiceImplTest {

    @Autowired
    MessageService messageService;

    @Test
    public void addMsg() {
        messageService.addMsg(new Message(16l,15l,0, "你是鸭儿", new Date()));
    }

    @Test
    public void listMessage() {
        ArrayList<MessageDetail> messageDetails = messageService.listMessage(16l, 15l, 3);
        System.out.println(messageDetails);
    }

    @Test
    public void listUnReadMessage() {
        ArrayList<MessageDetail> messageDetails = messageService.listUnReadMessage(15l, 16l);
        System.out.println(messageDetails);
    }

    @Test
    public void unReadMessageCount() {
        System.out.println(messageService.unReadMessageCount(15l, 16l));;
    }

    @Test
    public void getPage() {
        Page<MessageDetail> page = messageService.getPage(15l, 16l, 1l, 5l);
        System.out.println(page.getItems());
    }
}