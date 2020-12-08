package webqq.dao;

import webqq.domain.Result;
import webqq.domain.User;

import java.util.ArrayList;

public interface ResultDao {
    /**
     * 用户p的好友申请的结果，isread属性表示消息是否查阅,可以限制数目。
     * @param p 用户对象
     * @return 返回好友申请结果
     */
    public ArrayList<Result> listResult(User p );

    /**更新结果
     * 进行更新好友申请结果 主要用于改变isread属性
     * @param p
     */
    public void updateResult(Result p );

    /**增加结果
     * 进行增加请求结果，当用户接收到好友申请并进行处理后，(通过点击同意 拒绝后)，然后新建处理结果(等待验证)
     * @param p 表示新增的记录
     */
    public void insertResult(Result p ) ;
}
