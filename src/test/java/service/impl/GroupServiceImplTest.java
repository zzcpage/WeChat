package service.impl;

import domain.Group;
import domain.GroupMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.GroupService;

import java.util.ArrayList;

import static org.junit.Assert.*;
@ContextConfiguration("classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class GroupServiceImplTest {

    @Autowired
    GroupService groupService;

    @Test
    public void getGroups() {
        ArrayList<GroupMessage> groups = groupService.getGroups((long) 14) ;
        System.out.println(groups);


    }

    @Test
    public void getGroupByGID() {
        GroupMessage groupByGID = groupService.getGroupByGID((long) 2);
        System.out.println(groupByGID);
    }

    @Test
    public void updateGroup() {
        Group group = new Group("陌生人", 14l);
        group.setId(2l);
        groupService.updateGroup(group);
    }

    @Test
    public void addGroup() {
        Group group = new Group("陌生人", 15l);
        groupService.addGroup(group);
    }

    @Test
    public void deleteGroup() {
        groupService.deleteGroup(2l);
    }
}