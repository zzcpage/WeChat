package domain;

import java.util.Date;

public class MsgListDetail {
    private FriendMessage friend;
    private String lastMessage;
    private Date date;
    private int count ;

    public MsgListDetail(FriendMessage friend, String lastMessage, Date date) {
        this.friend = friend;
        this.lastMessage = lastMessage;
        this.date = date;
        this.count = 0 ;
    }

    public MsgListDetail(MsgList msgList, FriendMessage friendMessage, Boolean isSentBySelf) {
        this.date = msgList.getDate();
        this.lastMessage = msgList.getMsg();
        this.friend = friendMessage;
        this.count = 0 ;
    }
    public MsgListDetail(MsgList msgList, FriendMessage friendMessage,int count ) {
        this.date = msgList.getDate();
        this.lastMessage = msgList.getMsg();
        this.friend = friendMessage;
        this.count = count ;
    }
    public FriendMessage getFriend() {
        return friend;
    }

    public void setFriend(FriendMessage friend) {
        this.friend = friend;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "MsgListDetail{" +
                "friend=" + friend +
                ", lastMessage='" + lastMessage + '\'' +
                ", date=" + date +
                ", count=" + count +
                '}';
    }
}
