package controller;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.socket.*;
import domain.Message;
import controller.*;
import service.MessageService;
import service.UserService;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

//进行消息的处理,类似于OnMessage OnClose注解
//整个系统的消息处理
public class WebSocketHander implements WebSocketHandler {
    private static MessageService messageService  ;
    @Autowired
    public void setMessageService(MessageService messageService) {
        WebSocketHander.messageService = messageService;
    }
    private static UserService userService  ;
    @Autowired
    public void setUserService(UserService userService) {
        WebSocketHander.userService = userService;
    }

    //在线用户列表
//    private static final List<WebSocketSession> users = new ArrayList<WebSocketSession>();
    public static final Map<Long,WebSocketSession> users = new HashMap<>() ;

    /**
     * 初次连接成功,连接成功后的处理
     * @param webSocketSession
     * @throws Exception
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
        System.out.println("连接成功....");
        Long uid = (Long) webSocketSession.getAttributes().get("UID");
        //加入连接对象
        users.put(uid,webSocketSession) ;
        System.out.println("WebSocketHander"+uid);
        ApplicationContext act = ApplicationContextRegister.getApplicationContext() ;
        userService = act.getBean(UserService.class);
        userService.changeState(uid,1);
        String json = "{\"uidstate\":"+uid+",\"state\":\"在线\"}";
        sendMessageToUsers(new TextMessage(json)) ;
    }

    /**
     * 进行接受消息进行消息处理，也就是用户发送消息后的处理
     * @param webSocketSession
     * @param webSocketMessage
     * @throws Exception
     */
    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage)  {
        //对json数据解析
        System.out.println("进入消息处理");
        System.out.println(messageService);
        String params = (String)webSocketMessage.getPayload();
        System.out.println(params);
        ObjectMapper objectMapper = new ObjectMapper();
        HashMap<String,Object> map  ;
        try {
            map = objectMapper.readValue(params, HashMap.class);
            System.out.println("进行map的生成");
        }catch (Exception e){
            System.out.println("字符解析失败");
            return ;
        }
        if(map!=null) {

            Object message = map.get("messages");
            Object ruid = map.get("ruids");
            Object date = map.get("datas");
            int type = 1 ;//消息类型
            //int type = (Integer) webSocketSession.getAttributes().get("type");
            if (type == 1) {
                try {
                    //进行Object类型转化
                    Long rid = Long.valueOf(String.valueOf(ruid)) ;
                    System.out.println("接收者ID：" + rid);
                    String  msg = String.valueOf(message) ;
                    msg.replaceAll("(\r\n|\n)","<br/>");
                    System.out.println("更新后的字符串");
                    System.out.println(msg);
                    System.out.println(date);
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String d = format.format(date);
                    Date date1 = format.parse(d);
                    JSONObject jsonObject = new JSONObject(map) ;
                    Message message1 = new Message((Long) webSocketSession.getAttributes().get("UID"),rid,0,msg,date1);
                    sendMessageToUser(webSocketSession, message1, new TextMessage(params),jsonObject);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("信息发送失败");
                }
            }
        }else {
            System.out.println("map为空");
        }

    }

    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {
        if(webSocketSession.isOpen())
        {
            webSocketSession.close();
        }
        System.out.println("链接出错，关闭链接....");
        Long uid= (Long) webSocketSession.getAttributes().get("UID");
        ApplicationContext act = ApplicationContextRegister.getApplicationContext() ;
        userService = act.getBean(UserService.class);
        userService.changeState(uid,0);
        System.out.println("用户："+webSocketSession.getAttributes().get("UID")+"下线了");
        System.out.println("链接出错，关闭链接....");
        users.remove(uid) ;
        String json = "{\"uidstate\":"+uid+",\"state\":\"下线\"}";
        sendMessageToUsers(new TextMessage(json)) ;
    }

    /**
     * 用户关闭连接后
     * @param webSocketSession
     * @param closeStatus
     * @throws Exception
     */
    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
        System.out.println("链接关闭...."+closeStatus.toString());
        System.out.println("用户："+webSocketSession.getAttributes().get("UID")+"下线了");
        Long uid= (Long) webSocketSession.getAttributes().get("UID");
        ApplicationContext act = ApplicationContextRegister.getApplicationContext() ;
        userService = act.getBean(UserService.class);
        userService.changeState(uid,0);
        users.remove(uid) ;
        String json = "{\"uidstate\":"+uid+",\"state\":\"下线\"}";
        sendMessageToUsers(new TextMessage(json)) ;
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
    /**
     * 给所有在线用户发送消息
     *
     * @param message
     */
    public void sendMessageToUsers(TextMessage message)  {
        for(Map.Entry<Long,WebSocketSession> m:users.entrySet())
        {
            if(m.getValue().isOpen())
            {
                try {
                    m.getValue().sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    /**
     * 给某个用户发送消息,给单独用户发送消息
     *
     * @param message
     */
    public void sendMessageToUser(WebSocketSession send,Message msg, TextMessage message,JSONObject jsonObject) {
        //可以返回JSON数据给前端
        Long uid = msg.getRuid() ;
        System.out.println(uid+"   in send message to user");
        //获取Service
        ApplicationContext act = ApplicationContextRegister.getApplicationContext() ;
        messageService = act.getBean(MessageService.class);
        //如果当前用户在线，需要进一步判断是直接显示在网页上，还是保存到数据库显示未读消息
        if(users.containsKey(uid))
        {
            WebSocketSession user = users.get(uid);
            if(user.isOpen())
            {

                try {
                    //jsonObject.put("img",ruser.getHeadimg());
                    messageService.addMsg(msg);
                    jsonObject.put("id",msg.getId()) ;
                    System.out.println(jsonObject.toJSONString());
                    user.sendMessage(new TextMessage(jsonObject.toJSONString()));

                    //send.sendMessage(message);
                } catch (IOException e) {
                    messageService.addMsg(msg);
                    e.printStackTrace();
                }
            }else {
                messageService.addMsg(msg);
                System.out.println(uid+"：用户不在线");
                users.remove(uid);
            }
        }else {
            //增加未读消息,对发送者和接收者都进行处理
            messageService.addMsg(msg);
            //当前用户不在线，进行增加未读消息
            // send.sendMessage(new TextMessage("用户不在线"));
            System.out.println(uid+"：用户不在线");
        }
    }
}
