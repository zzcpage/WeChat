package webqq.dao;

import webqq.domain.Msg;

import java.util.ArrayList;

/**
 * 正常消息类的dao
 */
public interface MsgDao {
    /**
     * 用于加入消息记录到数据库
     * @param p
     */
    public void addMsg(Msg p );

    /**
     * 查询数据库中 双方的聊天记录 uid1发送给uid2 和 uid2 发送给 uid2的数据
     * select * from msg where (id1=uid1 and id2 = uid2) or (id1 = uid2 and id2 = uid1) order by dates desc limit 0,n ;
     * @param uid1  当前登录的用户uid
     * @param uid2  登录用户所聊天对象的uid
     * @param n     显示的记录数目
     * @return
     */
    public ArrayList<Msg> listMsg(int uid1, int uid2,int n );

    /**
     * SELECT * FROM table LIMIT 5 , 10
     * 用于获取分页数据
     * @param a 表示偏移量
     * @param b 表示最大的数目
     * @return  从a+1行到 a+b的记录
     */
    public ArrayList<Msg> listPageMsg(int a , int b );
}
