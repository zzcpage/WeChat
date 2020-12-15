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

    /**
     * 添加一个消息列表项
     * @param msgList
     */
    public void addMsgList(MsgList msgList);

    /**
     * 删除一个消息列表项
     * @param listId
     */
    public void deleteMsgList(Long listId);

    /**
     * 更新消息列表项（有新消息时调用）
     * @param msgList
     */
    public void updateMsgList(MsgList msgList);
}
