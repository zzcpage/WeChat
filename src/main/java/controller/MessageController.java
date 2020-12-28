package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import domain.Message;
import domain.MessageDetail;
import domain.User;
import service.MessageService;
import service.UserService;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.Array;
import java.rmi.server.UID;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
public class MessageController {
    @Autowired
    private MessageService messageService ;
    @Autowired
    private UserService userService ;

    @ResponseBody
    @RequestMapping("/history")
    public ArrayList<MessageDetail> listHistory(@RequestBody String datas, HttpSession session)
    {
        /*进行解析JSON*/
        ArrayList<Message> p ;
        ArrayList<MessageDetail> rs = new ArrayList<>() ;
        ObjectMapper objectMapper = new ObjectMapper() ;
        HashMap<String,Object> map;
        try {
            map = objectMapper.readValue(datas,HashMap.class);
            Long uid2  = (Long)Long.valueOf(String.valueOf(map.get("uid2")));
            Long uid1 = (Long)session.getAttribute("UID") ;
            long start = 0 ;
            long end = 10   ;
            rs = messageService.listMessage(uid1, uid2, 10);
            System.out.println(rs);
            return rs;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("获取历史消息失败\n");
        }
        System.out.println("获取历史消息失败\n");
        return null ;
    }

    @ResponseBody
    @RequestMapping("/moreHistory")
    public ArrayList<MessageDetail> moreHistory(HttpSession session, Long start, Long fuid)
    {
        Long uid = (Long) session.getAttribute("UID");
        if(uid == null || fuid == null)return new ArrayList<>();
        ArrayList<MessageDetail> messageDetails = messageService.listMessage(uid, fuid, start, 20);
        System.out.println(messageDetails);
        return messageDetails;
    }

    @ResponseBody
    @RequestMapping("/changeState")
    public String updateMessageState(@RequestBody String datas)
    {
        try {
            System.out.println("aaaaaaa");
            System.out.println(datas);
            ObjectMapper objectMapper = new ObjectMapper() ;
            Map<String,Object> map  ;
            map = objectMapper.readValue(datas,HashMap.class)  ;
            Long id = Long.valueOf(String.valueOf(map.get("ids")));
            //更新消息状态
            messageService.updateStatue(id);
            System.out.println("aaaaaaa");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "{}";
    }
}
