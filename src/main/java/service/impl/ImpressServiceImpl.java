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
        long countImpress = impressDao.getCountImpress(uid);
        List<Impress> impresses = impressDao.listImpress(uid,Math.max(countImpress - n, 0) ,n);
        List<ImpressDetail> ret = new ArrayList<>();
        for(Impress impress:impresses) {
            User user = userDao.getUserById(impress.getRuid());
            ret.add(new ImpressDetail(impress, user.getUname(), user.getHeadimg()));
        }
        return ret;
    }

    @Override
    public ImpressDetail getImpress(Long uid1, Long uid2) {
        Impress impress = impressDao.getImpress(uid1, uid2);
        if(impress == null)return null;
        User user = userDao.getUserById(impress.getRuid());
        return new ImpressDetail(impress, user.getUname(), user.getHeadimg());
    }


}
