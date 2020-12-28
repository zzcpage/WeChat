package service.impl;

import domain.Impress;
import domain.ImpressDetail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.ImpressService;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
@ContextConfiguration("classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class ImpressServiceImplTest {

    @Autowired
    private ImpressService impressService;
    @Test
    public void addImpress() {
        impressService.addImpress(new Impress(15l, 17l, new Date(), "鸡儿"));
    }

    @Test
    public void updateImpress() {
        Impress impress = new Impress(15l, 17l, new Date(), "鸭儿");
        impress.setId(4l);
        impressService.updateImpress(impress);

    }

    @Test
    public void deleteImpress() {
        impressService.deleteImpress(3l);
    }

    @Test
    public void getImpresses() {
        List<ImpressDetail> impresses = impressService.getImpresses(17l, 5);
        System.out.println(impresses);
    }

    @Test
    public void getImpress() {
        ImpressDetail impress = impressService.getImpressDetail(17l, 15l);
        System.out.println(impress);
    }
}