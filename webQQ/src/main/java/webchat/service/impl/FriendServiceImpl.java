package webchat.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webchat.dao.FriendDao;
import webchat.service.FriendService;

@Service("friendService")
public class FriendServiceImpl implements FriendService {
    @Autowired
    private FriendDao friendDao ;
    @Override
    public void test() {

    }
}
