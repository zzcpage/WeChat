package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Impress;
import domain.ImpressDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.ImpressService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller("impressController")
public class ImpressController {
    @Autowired
    private ImpressService impressService;

    /**
     * 获取你和其他人对你好友的印象（或其他人对你的印象）
     * @param fuid 该印象所有者的uid
     * @return
     * @throws JsonProcessingException
     */
    @ResponseBody
    @RequestMapping(value = "/getImpress", produces = "application/json;charset=UTF-8")
    public String getImpress(HttpSession session, @RequestParam("fuid")Long fuid) throws JsonProcessingException {
        Long uid = (Long) session.getAttribute("UID");
        if(uid == null) {
            return "error";
        }
        List<ImpressDetail> impresses = impressService.getImpresses(fuid, 20);

        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = mapper.writeValueAsString(impresses);

        return jsonStr;
    }

    /**
     * 获取你对某个好友的印象
     * @param fuid 好友uid
     * @return
     * @throws JsonProcessingException
     */
    @ResponseBody
    @RequestMapping(value = "/yourImpress", produces = "application/json;charset=UTF-8")
    public String yourImpress(HttpSession session, Long fuid) throws JsonProcessingException {
        Long uid = (Long) session.getAttribute("UID");
        if(uid == null) {
            return "error";
        }
        Impress impress = impressService.getImpress(fuid, uid);
        if(impress == null)return "{}";
        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = mapper.writeValueAsString(impress);
        return jsonStr;
    }

    /**
     * 往后获取20条别人对你好友的印象
     * @param fuid
     * @param start
     * @return
     * @throws JsonProcessingException
     */
    @ResponseBody
    @RequestMapping(value = "/moreImpress", produces = "application/json;charset=UTF-8")
    public String moreImpress(HttpSession session, @RequestParam("fuid")Long fuid, @RequestParam("start")Long start) throws JsonProcessingException {
        Long uid = (Long) session.getAttribute("UID");
        if(uid == null) {
            return "error";
        }
        System.out.println(fuid);
        System.out.println(start);
        List<ImpressDetail> impresses = impressService.getImpresses(fuid, start, 20);
        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = mapper.writeValueAsString(impresses);
        System.out.println(jsonStr);
        return jsonStr;
    }

    /**
     * 更新你对好友的印象
     * @param fuid 好友uid
     * @param date 日期
     * @param msg 印象
     * @return
     * @throws JsonProcessingException
     */
    @ResponseBody
    @RequestMapping(value = "/updateImpress", produces = "application/json;charset=UTF-8")
    public String updateImpress(HttpSession session, Long fuid, Date date, String msg) throws JsonProcessingException {
        Long uid = (Long) session.getAttribute("UID");
        if(uid == null) {
            return "error";
        }
        Impress impress = impressService.getImpress(fuid, uid);
        if(impress == null) {
            impress = new Impress(uid, fuid, date, msg);
            impressService.addImpress(impress);
            return "{}";
        }
        impress.setDate(date);
        impress.setMsg(msg);
        impressService.updateImpress(impress);
        return "{}";
    }

    /**
     * 删除好友对我的印象
     * @param fuid 好友uid
     * @return
     * @throws JsonProcessingException
     */
    @ResponseBody
    @RequestMapping(value = "/deleteImpress", produces = "application/json;charset=UTF-8")
    public String deleteImpress(HttpSession session, Long fuid) throws JsonProcessingException {
        Long uid = (Long) session.getAttribute("UID");
        if(uid == null) {
            return "error";
        }
        System.out.println(fuid);
        Impress impress = impressService.getImpress(uid, fuid);
        System.out.println(impress);
        impressService.deleteImpress(impress.getId());
        return "{}";
    }

}
