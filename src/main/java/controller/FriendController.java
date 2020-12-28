package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.FriendService;
import service.RequestService;
import service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller("friendController")
public class FriendController {

    @Autowired
    private RequestService requestService;

    @Autowired
    private FriendService friendService;

    @Autowired
    private UserService userService;

    /**
     * 获取当前用户的所有好友请求
     * @return
     * @throws JsonProcessingException
     */
    @ResponseBody
    @RequestMapping(value = "/getRequest", produces = "application/json;charset=UTF-8")
    public String getRequests(HttpSession session) throws JsonProcessingException {

        Long uid = (Long) session.getAttribute("UID");

        if(uid == null) {
            return "error";
        }
        List<RequestMessage> requestMessage = requestService.getRequestMessage(uid);
        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = mapper.writeValueAsString(requestMessage);
        System.out.println(jsonStr);
        return jsonStr;
    }

    /**
     * 更新好友请求
     * @param rid  接受者的uid
     * @param state 请求状态 1：接受 2：拒绝
     * @param sgroup 接收者在发送者的分组id
     * @return
     * @throws JsonProcessingException
     */
    @ResponseBody
    @RequestMapping(value = "/updateRequest", produces = "application/json;charset=UTF-8")
    public String updateRequest(HttpSession session, Long rid, int state, long sgroup) throws JsonProcessingException {

        Long uid = (Long) session.getAttribute("UID");
        if(uid == null) {
            return "error";
        }
        Request request1 = requestService.getRequestByRid(rid);
        System.out.println(request1);
        if(request1 == null)return "{}";
        request1.setState(state);
        request1.setSgroup(sgroup);
        requestService.updateRequest(request1);
        return "{}";
    }


    /**
     * 查找并返回好友信息
     * @param account 好友账号
     * @return
     * @throws JsonProcessingException
     */
    @ResponseBody
    @RequestMapping(value = "/searchFriend", produces = "application/json;charset=UTF-8")
    public String searchFriend(HttpSession session, String account) throws JsonProcessingException {
        Long uid = (Long) session.getAttribute("UID");
        if(uid == null) {
            return "error";
        }
        UserMessage usermsg = userService.findUserByAccount(account);
        if(usermsg == null)return "{}";
        Friend friend = friendService.getFriend(uid, usermsg.getUid());
        if(friend != null || usermsg.getUid() == uid) {
            return "true";
        }
        User user = userService.getUser(usermsg.getUid());
        user.setPassword("");
        if(usermsg == null)
        return "{}";
        else {
            ObjectMapper mapper = new ObjectMapper();
            String jsonStr = mapper.writeValueAsString(user);
            return jsonStr;
        }
    }


    /**
     * 通过好友uid删除好友
     * @param model
     * @param fuid 好友uid
     * @return
     * @throws JsonProcessingException
     */
    @ResponseBody
    @RequestMapping("/deleteFriend")
    public String deleteFriend(HttpSession session, Model model, Long fuid) throws JsonProcessingException {
        Long uid = (Long) session.getAttribute("UID");
        if(uid == null) {
            return "error";
        }
        friendService.deleteFriendById(uid, fuid);
        return "{}";
    }

    @ResponseBody
    @RequestMapping("/changeGroup")
    public String changeGroup(HttpSession session, Long fuid, Long newgid) throws JsonProcessingException {
        Long uid = (Long) session.getAttribute("UID");
        if(uid == null) {
            return "error";
        }
        friendService.updataFriendInfo(uid ,fuid , newgid);
        return "{}";
    }

    /**
     * 发送好友请求
     * @param fuid 好友id
     * @param rgroup 接收者在发送者的分组
     * @param remark 附加信息
     * @return
     * @throws JsonProcessingException
     */
    @ResponseBody
    @RequestMapping(value = "/sendRequest", produces = "application/json;charset=UTF-8")
    public String sendRequest(HttpSession session, Long fuid, Long rgroup, String remark) throws JsonProcessingException {
        Long uid = (Long) session.getAttribute("UID");
        if(uid == null) {
            return "error";
        }
        Request request2 = requestService.getRequest(fuid, uid);
        if(request2!=null)return "{}";
        Request request1 = new Request(null, uid, fuid, remark, 0, null, rgroup, new Date());
        requestService.addRequest(request1);
        return "{}";
    }

    /**
     * 获取好友信息
     * @param fuid 好友uid
     * @return
     * @throws JsonProcessingException
     */
    @ResponseBody
    @RequestMapping(value = "/getFriendInfo", produces = "application/json;charset=UTF-8")
    public String getFriendInfo(HttpSession session, Long fuid) throws JsonProcessingException {
        Long uid = (Long) session.getAttribute("UID");
        if(uid == null) {
            return "error";
        }
        System.out.println(uid + " " + fuid);

        FriendMessage friend = friendService.getFriendById(uid, fuid);

        if(friend==null)return "{}";
        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = mapper.writeValueAsString(friend);
        System.out.println(jsonStr);
        return jsonStr;
    }

    @ResponseBody
    @RequestMapping(value = "/listfriend", produces = "application/json;charset=UTF-8")
    public ArrayList<UserBox> listFriend(@RequestBody String datas, HttpSession session)
    {
        /* 进行解析JSON数据 */
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println("FriendList:请求分组GID："+datas);
        HashMap<String,Object> map  ;
        try {
            map = objectMapper.readValue(datas, HashMap.class);
            Long uid  =  (Long)session.getAttribute("UID") ;
            Long gid  = Long.valueOf(String.valueOf(map.get("gids")));
            ArrayList<Friend> temp = friendService.listFriend(uid,gid);
            ArrayList<UserBox>  p = new ArrayList<>() ;
            System.out.println(uid);
            User user  ;
            for(Friend friend:temp)
            {
                if(friend.getUid1()==uid)
                {
                    user = userService.getUser(friend.getUid2());
                    UserBox temps =  new UserBox(user.getUid(),user.getUname(),user.getAccount(),user.getBirthday(),user.getSex(),user.getEmail(),user.getHeadimg(),user.getState(),user.getSignature()) ;
                    p.add(temps);
                } else {
                    user = userService.getUser(friend.getUid1());
                    UserBox temps =  new UserBox(user.getUid(),user.getUname(),user.getAccount(),user.getBirthday(),user.getSex(),user.getEmail(),user.getHeadimg(),user.getState(),user.getSignature()) ;
                    p.add(temps);
                }
            }
            System.out.println("请求分组好友结果:"+temp);
            System.out.println("请求分组好友:"+p);
            return p ;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("获取分组好友失败");
        }
        //进行数据处理，完成响应
        return null ;
    }

    @ResponseBody
    @RequestMapping(value = "/getFriendGroup", produces = "application/json;charset=UTF-8")
    public String getFriendGroup(HttpSession session, Long fuid) throws JsonProcessingException {
        Long uid = (Long) session.getAttribute("UID");
        if(uid == null) {
            return "error";
        }
        Friend friend = friendService.getFriend(uid, fuid);
        Long returnGroup = 0L;
        if(friend==null)return "{}";

        if(friend.getUid1() == uid) {
            returnGroup = friend.getGroup2();
        } else {
            returnGroup = friend.getGroup1();
        }
        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = mapper.writeValueAsString(returnGroup);
        System.out.println(jsonStr);
        return jsonStr;
    }

}
