package webqq.dao;

import webqq.domain.UnSentMsg;

import java.util.ArrayList;

/**
 * 为发送消息的Dao
 */
public interface UnSentMsgDao {
    /**
     * 如果有没有发送的消息，进行存储
     * @param p 未发送消息对象
     */
    public void insertUnSentMsg(UnSentMsg p);

    /**
     * 当用户上线接收到指定消息后进行消息的发送和删除
     * @param p 删除未发送的消息
     */
    public void deleteUnsentMsg(UnSentMsg p );

    /**
     * 当用户上线后查询未发送表，进行消息的发送
     * @param uid  登录用户的uid
     * @return  返回接收到的未发送消息
     */
    public ArrayList<UnSentMsg> listUnSendMsg(int uid) ;
}
