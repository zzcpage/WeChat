package webchat.controller;

import org.springframework.ui.Model ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import webchat.domain.User;
import webchat.service.UserService;

import java.util.ArrayList;

@Controller
@RequestMapping("/user")
public class UserController {
    //依赖注入
    @Autowired
    private UserService userService;
    @RequestMapping("/findAll")
    public String findAll(Model module) throws Exception {
        System.out.println("表现层查询");
        //调用service,进行处理
        ArrayList<User> users = new ArrayList<>() ;
        return "list" ;
    }
}
