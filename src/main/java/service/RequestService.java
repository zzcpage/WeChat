package service;

import domain.Request;
import domain.RequestMessage;

import java.util.List;

public interface RequestService {
    /**
     * 获得当前用户收到的的所有好友请求
     * @param uid 用户uid
     * @return
     */
    public List<RequestMessage> getRequest(Long uid);

    /**
     * 更新好友请求
     * @param request 好友请求
     */
    public void updateRequest(Request request);

    /**
     * 增加好友请求
     * @param request 好友请求
     */
    public void addRequest(Request request);
}
