package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Impress;
import domain.User;
import domain.UserMessage;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.socket.WebSocketHandler;
import service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

@Controller("userController")
public class UserController {
    @Autowired
    private UserService userService;

    //登录
    @RequestMapping(value = "/login", produces = "application/json;charset=UTF-8")
    public String login(HttpServletRequest request, HttpSession session, HttpServletResponse response, Model model, @RequestParam("account") String account, @RequestParam("password") String password) {
        UserMessage loginUser = userService.login(account, password);
        System.out.println(loginUser);
        if(loginUser != null) {
            model.addAttribute("user", loginUser);
            session.setAttribute("UID", loginUser.getUid());
            return "main";
        } else if(loginUser == null) {
            request.setAttribute("errorMsg", "用户名密码组合不正确！");
            return "login";
        } else {
            request.setAttribute("errorMsg", "该用户已在线，不能重复登录！");
            return "login";
        }
    }


    /**
     * 注册
     * @param model
     * @param password 密码
     * @param name 昵称
     * @return
     */
    @RequestMapping(value = "/register", produces = "application/json;charset=UTF-8")
    public String register(HttpServletRequest request, Model model, String password, String name, String yzm) {

        //获取服务器生成的验证码
        String trueyzm = (String) request.getSession().getAttribute("KAPTCHA_SESSION_KEY");
        //获取之后立刻销毁，防止表单重复提交
        request.getSession().removeAttribute("KAPTCHA_SESSION_KEY");
        if(yzm==null ||!yzm .equals(trueyzm) ) {
            model.addAttribute("errorMsg", "验证码错误");
            return "register";
        }
        User user = new User(name, password);
        String register = userService.register(user);
        System.out.println(register);
        model.addAttribute("account", register);
        return "regester_success";
    }

    /**
     * 更新用户信息
     * @param request
     * @param upload 上传的图片
     * @param username 昵称
     * @param sex 性别
     * @param birthday 生日
     * @param signature 个性签名
     * @return
     * @throws IOException
     * @throws ParseException
     */
    @ResponseBody
    @RequestMapping(value = "/update", produces = "application/json;charset=UTF-8")
    public String update(HttpServletRequest request, HttpSession session, MultipartFile upload, String username, String sex, String birthday, String signature, String email) throws IOException, ParseException {
        Long uid = (Long) session.getAttribute("UID");
        if(uid == null) {
            return "error";
        }
        User user = userService.getUser(uid);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse(birthday);

        user.setBirthday(date);
        user.setSex(sex);
        user.setEmail(email);
        if(username != null && username.length() > 0)
            user.setUname(username);
        user.setSignature(signature);
        if(upload != null){
            String path = request.getSession().getServletContext().getRealPath("/static/images/");
            File file = new File(path);
            if(!file.exists()){
                file.mkdir();
            }
            String filename = upload.getOriginalFilename();
            String uuid = UUID.randomUUID().toString().replace("-", "");
            filename = uuid + filename;
            upload.transferTo(new File(path, filename));
            user.setHeadimg("static/images/"+filename);
            userService.updataUser(user);
            return "{" + "\"headimg\":\""+user.getHeadimg()+"\"}";
        }
        userService.updataUser(user);
        return "{}";
    }


    /**
     * 更新密码
     * @param originPwd 原始密码
     * @param newPwd 新密码
     * @return
     * @throws IOException
     * @throws ParseException
     */
    @ResponseBody
    @RequestMapping(value = "/changePwd", produces = "application/json;charset=UTF-8")
    public String changePwd(HttpSession session,  String originPwd, String newPwd) throws IOException, ParseException {
        Long uid = (Long) session.getAttribute("UID");

        if(uid == null) {
            return "error";
        }
        User user = userService.getUser(uid);
        if(user == null)return "false";
        if(user.getPassword().equals(originPwd)) {
            user.setPassword(newPwd);
            userService.updataUser(user);
            return "true";
        }
        return "false";
    }

    //跳转到登录界面
    @RequestMapping(value = "/toLogin", produces = "application/json;charset=UTF-8")
    public String toLogin(HttpServletRequest request, HttpServletResponse response, Model model, MultipartFile upload, String originPwd, String newPwd) throws IOException, ParseException {
        return "login";
    }
    //跳转到注册界面
    @RequestMapping(value = "/toRegister", produces = "application/json;charset=UTF-8")
    public String toRegister(HttpServletRequest request, HttpServletResponse response, Model model, MultipartFile upload, String originPwd, String newPwd) throws IOException, ParseException {
        return "register";
    }

    /**
     * 获取用户信息
     * @return
     * @throws JsonProcessingException
     */
    @ResponseBody
    @RequestMapping(value = "/getUserInfo", produces = "application/json;charset=UTF-8")
    public String getUserInfo(HttpSession session) throws JsonProcessingException {
        Long uid = (Long) session.getAttribute("UID");
        if(uid == null) {
            return "{}";
        }
        User user = userService.getUser(uid);
        //隐藏密码
        user.setPassword("");
        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = mapper.writeValueAsString(user);
        return jsonStr;
    }


    /**
     * 传入好友UID 返回好友的个性签名
     * @param states 好友UID
     * @param session 无作用
     * @return
     */
    @ResponseBody
    @RequestMapping(path = "/userlogin",method = {RequestMethod.POST})
    public void changeState(@RequestBody String states, HttpSession session)
    {
        ObjectMapper objectMapper = new ObjectMapper();
        HashMap<String,Object> map  ;
        try {
            map = objectMapper.readValue(states, HashMap.class);
            Long uid = (Long)session.getAttribute("UID")  ;
            int  state  =  Integer.valueOf(String.valueOf(map.get("states")));
            User p = userService.getUser(uid) ;
            p.setState(state);
            userService.updataUser(p);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("修改用户登录状态失败");
        }
    }

    //获取在线状态
    @ResponseBody
    @RequestMapping(path = "/getstate",produces = "application/json;charset=UTF-8")
    public String login(@RequestBody String datas,HttpServletResponse response) {
        ObjectMapper objectMapper = new ObjectMapper();
        HashMap<String,Object> map = new HashMap<>() ;
        String state ;
        try {
            map = objectMapper.readValue(datas, HashMap.class);
            Long uid = Long.valueOf(String.valueOf(map.get("ruid")));
            if(WebSocketHander.users.containsKey(uid))
            {
                state = "{\"state\":\"在线\"}" ;
                return state ;
            }else {
                state = "{\"state\":\"离线\"}" ; ;
                return state ;
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("修改用户登录状态失败");
        }
        state = "{\"state\":\"离线\"}" ; ;
        return state ;
    }

}
