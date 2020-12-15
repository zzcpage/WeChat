package domain;

import java.util.Date;

public class MsgListDetail {
    private FriendMessage friend;
    private String lastMessage;
    private Date date;
    private Boolean isSentBySelf;

    public MsgListDetail(FriendMessage friend, String lastMessage, Date date) {
        this.friend = friend;
        this.lastMessage = lastMessage;
        this.date = date;
        this.isSentBySelf = false;
    }

    public MsgListDetail(MsgList msgList, FriendMessage friendMessage, Boolean isSentBySelf) {
        this.date = msgList.getDate();
        this.lastMessage = msgList.getMsg();
        this.friend = friendMessage;
        this.isSentBySelf = isSentBySelf;
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

    public Boolean getSentBySelf() {
        return isSentBySelf;
    }

    public void setSentBySelf(Boolean sentBySelf) {
        isSentBySelf = sentBySelf;
    }

    @Override
    public String toString() {
        return "MsgListDetail{" +
                "friend=" + friend +
                ", lastMessage='" + lastMessage + '\'' +
                ", date=" + date +
                ", isSentBySelf=" + isSentBySelf +
                '}';
    }
}
