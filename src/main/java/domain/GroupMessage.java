package domain;

import java.util.List;

public class GroupMessage {
    private Long groupId;
    private String groupName;
    private List<FriendMessage> friends;

    public GroupMessage() {
    }

    public GroupMessage(Long groupId, String groupName, List<FriendMessage> friends) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.friends = friends;
    }

    public GroupMessage(Group group, List<FriendMessage> friendMessages) {
        this.groupId = group.getId();
        this.groupName = group.getgName();
        this.friends = friendMessages;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<FriendMessage> getFriends() {
        return friends;
    }

    public void setFriends(List<FriendMessage> friends) {
        this.friends = friends;
    }
}
