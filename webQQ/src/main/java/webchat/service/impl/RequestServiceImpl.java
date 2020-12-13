package webchat.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webchat.dao.RequestDao;
import webchat.domain.Request;
import webchat.service.RequestService;

import java.sql.Date;
import java.util.ArrayList;

@Service("requestService")
public class RequestServiceImpl implements RequestService {
    //依赖注入
    @Autowired
    private RequestDao requestDao;
    @Override
    public void test() {
        Request p ;
        ArrayList<Request> q = new ArrayList<>() ;
        //测试增加
//        Request p = new Request() ;
//        p.setSuid(1);
//        p.setRuid(2);
//        p.setRgroup(1);
//        p.setState(0);
//        p.setDate(new Date(2019-1900,4,02));
//        System.out.println(p.getDate());
//        p.setRemark("你是鸡儿");
//        requestDao.insertRequest(p);
//        System.out.println(requestDao.getRequest(1,2));
        p  = requestDao.getRequest(1,2) ;
        if(p==null)
            System.out.println("yes");
        q = requestDao.listSendRequest(1);
        System.out.println(q);
        q = requestDao.listAllRequest(1);
        System.out.println(q);
        q = requestDao.listRequest(1);
        System.out.println(q);
        requestDao.updateRequest(p);
        System.out.println("in");
    }
}
