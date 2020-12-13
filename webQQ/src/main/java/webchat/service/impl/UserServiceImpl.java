package webchat.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webchat.dao.UserDao;
import webchat.domain.User;
import webchat.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
    //依赖注入
    @Autowired
    private UserDao userDao;

}
