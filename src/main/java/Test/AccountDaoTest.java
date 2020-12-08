package Test;

import dao.AccountDao;
import domain.Account;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.*;

public class AccountDaoTest {

    @Test
    public void findAll() throws Exception {
        //加载配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        SqlSession session = factory.openSession();

        //获取代理对象
        AccountDao dao = session.getMapper(AccountDao.class);

        List<Account> all = dao.findAll();

        for(Account account:all) {
            System.out.println(account);
        }
        session.close();
        resourceAsStream.close();
    }

    @Test
    public void insert() throws Exception {
        //加载配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        SqlSession session = factory.openSession();

        //获取代理对象
        AccountDao dao = session.getMapper(AccountDao.class);


        Account account = new Account();
        account.setName("crk");
        account.setMoney("10000");
        dao.saveAccount(account);

        //提交事务
        session.commit();

        session.close();
        resourceAsStream.close();
    }
}