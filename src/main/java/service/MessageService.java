package service;

import domain.Message;
import domain.MessageDetail;
import domain.MsgListDetail;
import domain.Page;

import java.util.ArrayList;

public interface MessageService {
    /**
     * 添加消息记录
     * @param p 消息
     */
    public void addMsg(Message p);

    /**
     * 查询两个用户的n条聊天记录（不包含未读信息，如果有未读信息，返回n+未读信息条）
     * @param uid1 当前用户uid
     * @param uid2 好友uid
     * @param n 记录条数
     * @return
     */
    public ArrayList<MessageDetail> listMessage(Long uid1, Long uid2, Integer n);


    /**
     * 查询 uid1发送给uid2 的所有未读消息
     * @param uid1
     * @param uid2
     * @return
     */
    public ArrayList<MessageDetail> listUnReadMessage(Long uid1, Long uid2);


    /**
     * 查询 uid1发送给uid2 的所有未读消息条数
     * @param uid1
     * @param uid2
     * @return
     */
    public Integer unReadMessageCount(Long uid1, Long uid2);



    /**
     * 获取分页后的消息数据
     * @param uid 当前用户uid
     * @param friendUid 好友uid
     * @param pageNo 当前页码
     * @param pageSize 每页几条数据
     * @return
     */
    public Page<MessageDetail> getPage(Long uid, Long friendUid, Long pageNo, Long pageSize);

}
