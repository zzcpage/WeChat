package webchat.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webchat.dao.ImpressDao;
import webchat.domain.Impress;
import webchat.service.ImpressService;

import java.util.ArrayList;
import java.util.Date;

@Service("impressService")
public class ImpressServiceImpl implements ImpressService {
    @Autowired
    private ImpressDao impressDao ;
    @Override
    public void test() {
//        Impress p = new Impress() ;
//        p.setDate(new Date(2019-1900,4,6));
//        p.setMsg("你好，世界");
//        p.setSuid(1);
//        p.setRuid(2);
//        impressDao.insertImpress(p);
        Impress q ;
        ArrayList<Impress> p1 = new ArrayList<>() ;
        p1 = impressDao.listImpress(1,0,10);
        q = p1.get(0) ;
        System.out.println(impressDao.getCountMessage(1,2));
        System.out.println(impressDao.listMessagePage(1,2,1,10));
        System.out.println(p1);
        impressDao.deleteImpress(q);
    }
}
