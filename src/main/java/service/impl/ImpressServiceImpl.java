package service.impl;

import dao.ImpressDao;
import dao.UserDao;
import domain.Impress;
import domain.ImpressDetail;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import service.ImpressService;

import java.util.ArrayList;
import java.util.List;
@Service("impressService")
public class ImpressServiceImpl implements ImpressService {

    @Autowired
    private ImpressDao impressDao;

    @Autowired
    @Qualifier("userDao")
    private UserDao userDao;

    @Override
    public void addImpress(Impress impress) {
        impressDao.insertImpress(impress);
    }

    @Override
    public void updateImpress(Impress impress) {
        impressDao.updateImpress(impress);
    }

    @Override
    public void deleteImpress(Long impressId) {
        impressDao.deleteImpress(impressId);
    }

    @Override
    public List<ImpressDetail> getImpresses(Long uid, Integer n) {
        List<Impress> impresses = impressDao.listImpress(uid,0 ,n);
        List<ImpressDetail> ret = new ArrayList<>();
        for(Impress impress:impresses) {
            System.out.println(impress);
            User user = userDao.getUserById(impress.getSuid());
            System.out.println(user);
            ret.add(new ImpressDetail(impress, user.getUname(), user.getHeadimg()));
        }
        return ret;
    }

    @Override
    public List<ImpressDetail> getImpresses(Long uid, Long start, Integer n) {
        List<Impress> impresses = impressDao.listImpress(uid,start ,n);
        List<ImpressDetail> ret = new ArrayList<>();
        for(Impress impress:impresses) {
            User user = userDao.getUserById(impress.getSuid());
            ret.add(new ImpressDetail(impress, user.getUname(), user.getHeadimg()));
        }
        return ret;
    }

    @Override
    public ImpressDetail getImpressDetail(Long uid1, Long uid2) {
        Impress impress = impressDao.getImpress(uid1, uid2);
        if(impress == null)return null;
        User user = userDao.getUserById(impress.getSuid());
        return new ImpressDetail(impress, user.getUname(), user.getHeadimg());
    }

    @Override
    public Impress getImpress(Long uid1, Long uid2) {
        return impressDao.getImpress(uid1, uid2);
    }


}
