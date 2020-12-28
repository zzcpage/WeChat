package service.impl;

import domain.Request;
import domain.RequestMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.RequestService;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
@ContextConfiguration("classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class RequestServiceImplTest {

    @Autowired
    private RequestService requestService;
    @Test
    public void getRequest() {
        List<RequestMessage> request = requestService.getRequestMessage(16l);
        System.out.println(request);
    }

    @Test
    public void updateRequest() {
        Request request = new Request(null, 15l, 17l, "你是鸭儿", 1, 6l, 4l, new Date());
        request.setId(10l);
        requestService.updateRequest(request);
    }

    @Test
    public void addRequest() {
        Request request = new Request(null, 15l, 17l, "你是鸭儿", 0, null, 4l, new Date());
        requestService.addRequest(request);
    }
}