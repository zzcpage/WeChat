package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Group;
import domain.Impress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.GroupService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller("groupController")
public class GroupController {

    @Autowired
    private GroupService groupService;

    /**
     * 获取当前用户所有分组
     * @return
     * @throws JsonProcessingException
     */
    @ResponseBody
    @RequestMapping(value = "/getGroup", produces = "application/json;charset=UTF-8")
    public String getGroup(HttpSession session) throws JsonProcessingException {
        Long uid = (Long) session.getAttribute("UID");
        if(uid == null) {
            return "error";
        }
        List<Group> groups = groupService.getAllGroup(uid);
        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = mapper.writeValueAsString(groups);
        System.out.println(jsonStr);
        return jsonStr;
    }

    /**
     * 添加一个新分组
     * @param request
     * @param gname
     * @return
     * @throws JsonProcessingException
     */
    @ResponseBody
    @RequestMapping(value = "/addGroup", produces = "application/json;charset=UTF-8")
    public String addGroup(HttpSession session, String gname) throws JsonProcessingException {
        Long uid = (Long) session.getAttribute("UID");
        if(uid == null) {
            return "error";
        }
        if(gname == "")return "{}";
        groupService.addGroup(new Group(gname, uid));
        return "{}";
    }


    @RequestMapping(value = "/tajax", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ArrayList<Group> getGroup02(HttpSession session)  {
        /* 进行解析JSON数据   */
            Long uid  = (Long) session.getAttribute("UID");
            ArrayList<Group> p = (ArrayList<Group>) groupService.getAllGroup(uid);
            return p;
        //进行数据处理，完成响应
    }

}
