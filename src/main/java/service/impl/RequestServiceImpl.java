package service.impl;

import dao.FriendDao;
import dao.RequestDao;
import dao.UserDao;
import domain.Friend;
import domain.Request;
import domain.RequestMessage;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import service.RequestService;

import java.util.ArrayList;
import java.util.List;

@Service("requestService")
public class RequestServiceImpl implements RequestService {

    @Autowired
    private RequestDao requestDao;

    @Autowired
    @Qualifier("userDao")
    private UserDao userDao;

    @Autowired
    private FriendDao friendDao;

    @Override
    public List<RequestMessage> getRequest(Long uid) {
        List<Request> requests = requestDao.listRequest(uid);
        List<RequestMessage> ret = new ArrayList<>();
        for(Request request:requests) {
            //获取发送方的信息
            User user = userDao.getUserById(request.getSuid());
            ret.add(new RequestMessage(request, user.getUname(), user.getHeadimg()));
        }
        return ret;
    }

    @Override
    public void updateRequest(Request request) {
        //如果已经是好友，直接返回
        Friend friend = friendDao.getFriend(request.getRuid(), request.getSuid());
        if(friend!=null)return;
        Request request1 = requestDao.getRequest(request.getRuid(), request.getSuid());
        //只有该请求未处理时，才更新
        if(request1==null || !request1.getState().equals(0))return;
//        request1.setSgroup(request.getRgroup());
//        request1.setState(request.getState());
        requestDao.updateRequest(request);
        if(!request.getState().equals(1))return;
        //如果成功，插入一条新好友记录
        friendDao.insertFriend(new Friend(request.getSuid(), request.getRuid(), request.getSgroup(), request.getRgroup()));
    }

    @Override
    public void addRequest(Request request) {
        Friend friend = friendDao.getFriend(request.getSuid(), request.getRuid());
        if(friend != null)return;
        //获得未处理的好友请求
        Request request1 = requestDao.getRequest(request.getSuid(), request.getRuid());
        if(request1 != null) {
            request1.setState(1);
            request1.setSgroup(request.getRgroup());
            updateRequest(request1);
        } else {
            requestDao.insertRequest(request);
        }
    }
}
