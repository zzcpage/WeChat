package service.impl;

import dao.MsgListDao;
import domain.FriendMessage;
import domain.MsgList;
import domain.MsgListDetail;
import org.springframework.beans.factory.annotation.Autowired;
import service.FriendService;
import service.MsgListService;

import java.util.ArrayList;

public class MsgListServiceImpl implements MsgListService {

    @Autowired
    private MsgListDao msgListDao;

    @Autowired
    private FriendService friendService;

    @Override
    public ArrayList<MsgListDetail> getMessageListByUid(Long uid) {
        ArrayList<MsgListDetail> ret = new ArrayList<>();
        ArrayList<MsgList> lists = msgListDao.getListByUid(uid);
        for(MsgList msgList:lists) {
            Long suid = msgList.getSuid();
            MsgListDetail detail;
            FriendMessage friend = friendService.getFriendById(uid, msgList.getRuid());
            detail = new MsgListDetail(msgList, friend,false);
            ret.add(detail);
        }
        return ret;
    }

    @Override
    public void addMsgList(MsgList msgList) {
        msgListDao.addMsgList(msgList);
    }

    @Override
    public void deleteMsgList(Long listId) {
        msgListDao.deleteMsgList(listId);
    }

    @Override
    public void updateMsgList(MsgList msgList) {
        msgListDao.updateMsgList(msgList);
    }
}
