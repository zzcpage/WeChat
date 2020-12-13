package webchat.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webchat.dao.GroupDao;
import webchat.service.GroupService;
@Service("groupService")
public class GroupServiceImpl implements GroupService {
    @Autowired
    private GroupDao groupDao ;
    @Override
    public void test() {

    }
}
