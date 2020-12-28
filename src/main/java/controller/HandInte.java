package controller;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;
import domain.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
/**
 * 拦截器（握手）,类似于open注解，进行建立连接
 */
public class HandInte implements HandshakeInterceptor {
    @Override
    public boolean beforeHandshake(ServerHttpRequest request,
                                   ServerHttpResponse response, WebSocketHandler handler,
                                   Map<String, Object> map) throws Exception {
        if (request instanceof ServletServerHttpRequest) {
            HttpServletRequest req = ((ServletServerHttpRequest) request).getServletRequest();
            // 使用userName区分WebSocketHandler，以便定向发送消息,区分不同的连接对象
            // String userName = (String)
            // session.getAttribute("WEBSOCKET_USERNAME");
            Long UID = (Long) req.getSession().getAttribute("UID") ;
            //type 可以扩展为群聊
            //int type =  (Integer) req.getSession().getAttribute("type") ;
            System.out.println("Handler:"+UID);
            // System.out.println(type);
            map.put("UID",UID) ;
            //map.put("type",type);
        }
        System.out.println("outs");
        return true ;
    }
    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest,
                               ServerHttpResponse serverHttpResponse,
                               WebSocketHandler webSocketHandler, Exception e) {
    }
}
