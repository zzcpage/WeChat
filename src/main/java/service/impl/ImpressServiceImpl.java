package service.impl;

import dao.ImpressDao;
import dao.UserDao;
import domain.Impress;
import domain.ImpressDetail;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.ImpressService;

import java.util.ArrayList;
import java.util.List;
@Service("impressService")
public class ImpressServiceImpl implements ImpressService {

    @Autowired
    private ImpressDao impressDao;

    @Autowired
    private UserDao userDao;

    @Override
    public void addImpress(Impress impress) {
        impressDao.addImpress(impress);
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
        List<Impress> impresses = impressDao.getImpresses(uid, n);
        List<ImpressDetail> ret = new ArrayList<>();
        for(Impress impress:impresses) {
            User user = userDao.getUserById(impress.getRuid());
            ret.add(new ImpressDetail(impress, user.getUname(), user.getHeadImg()));
        }
        return ret;
    }

    @Override
    public ImpressDetail getImpress(Long uid1, Long uid2) {
        Impress impress = impressDao.getImpress(uid1, uid2);
        User user = userDao.getUserById(impress.getRuid());
        return new ImpressDetail(impress, user.getUname(), user.getHeadImg());
    }


}
