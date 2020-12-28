package domain;

import java.io.Serializable;

/**
 * 好友类
 * id1 表示好友1
 * id2 表示好友2
 * group1 表示好友2 在好友1 的位置,与group的id对应
 * group2 表示好友1 在好友2 的位置,与group的id对应
 */
public class Friend implements Serializable {
    private Long id;
    private Long uid1;
    private Long uid2;
    private Long group1;
    private Long group2;

    public Friend() {
    }

    public Friend(Long uid1, Long uid2, Long group1, Long group2) {
        this.uid1 = uid1;
        this.uid2 = uid2;
        this.group1 = group1;
        this.group2 = group2;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUid1() {
        return uid1;
    }

    public void setUid1(Long uid1) {
        this.uid1 = uid1;
    }

    public Long getUid2() {
        return uid2;
    }

    public void setUid2(Long uid2) {
        this.uid2 = uid2;
    }

    public Long getGroup1() {
        return group1;
    }

    public void setGroup1(Long group1) {
        this.group1 = group1;
    }

    public Long getGroup2() {
        return group2;
    }

    public void setGroup2(Long group2) {
        this.group2 = group2;
    }
    @Override
    public String toString() {
        return "Friend{" +
                "id=" + id +
                ", uid1=" + uid1 +
                ", uid2=" + uid2 +
                ", group1=" + group1 +
                ", group2=" + group2 +
                '}';
    }

}

