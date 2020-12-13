package service;

import domain.MsgList;
import domain.MsgListDetail;

import java.util.ArrayList;

public interface MsgListService {

    /**
     * 获取当前用户的消息列表
     * @param uid
     * @return
     */
    public ArrayList<MsgListDetail> getMessageListByUid(Long uid);

    public void addMsgList(MsgList msgList);

    public void deleteMsgList(Long listId);

    public void updateMsgList(MsgList msgList);
}
