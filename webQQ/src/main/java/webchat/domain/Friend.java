package webchat.domain;

/**
 * id 好友关系序号
 * uid1 用户1
 * uid2 用户2
 * group1 用户1在用户2的分组
 * group2 用户2在用户1的分组
 */
public class Friend {
    private long id ;
    private long uid1 ;
    private long uid2 ;
    private long group1 ;
    private long group2 ;
    public Friend(){}
    public Friend(long uid1, long uid2, long group2) {
        this.uid1 = uid1;
        this.uid2 = uid2;
        this.group2 = group2;
    }

    public Friend(long uid1, long uid2, long group1, long group2) {
        this.uid1 = uid1;
        this.uid2 = uid2;
        this.group1 = group1;
        this.group2 = group2;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUid1() {
        return uid1;
    }

    public void setUid1(long uid1) {
        this.uid1 = uid1;
    }

    public long getUid2() {
        return uid2;
    }

    public void setUid2(long uid2) {
        this.uid2 = uid2;
    }

    public long getGroup1() {
        return group1;
    }

    public void setGroup1(long group1) {
        this.group1 = group1;
    }

    public long getGroup2() {
        return group2;
    }

    public void setGroup2(long group2) {
        this.group2 = group2;
    }

    @Override
    public String toString() {
        return "friend{" +
                "id=" + id +
                ", uid1=" + uid1 +
                ", uid2=" + uid2 +
                ", group1=" + group1 +
                ", group2=" + group2 +
                '}';
    }
}
