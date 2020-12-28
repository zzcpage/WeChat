package controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import domain.MsgListDetail;
import service.MessageService;
import service.MsgListService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class MessageListController {
    @Autowired
    private MsgListService msgListService  ;
    @ResponseBody
    @RequestMapping("/getmsglist")
    public ArrayList<MsgListDetail> getMsgList(HttpSession session)
    {
        ArrayList<MsgListDetail> p = new ArrayList<>() ;
        Long uid = (Long) session.getAttribute("UID");
        p = msgListService.getMsgList(uid);
        System.out.println(p);
        return p ;
    }
}

