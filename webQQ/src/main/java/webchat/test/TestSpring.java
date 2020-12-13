package webchat.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import webchat.dao.UserDao;
import webchat.domain.User;
import webchat.service.*;

public class TestSpring {
    @Test
    public void test1() throws Exception {
        //加载配置文件
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        //获取对象
        ImpressService as = (ImpressService) ac.getBean("impressService");
        //调用方法
        as.test();
    }
}
