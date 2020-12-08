package webqq.domain;

import java.io.Serializable;

/**
 * 分组类
 * id 表示分组序号
 * groupName 表示分组名称
 * Uid 表示分组所属的用户Uid
 * pos 表示属于用户的第几个分组(用户有多个分组，需要确定位置)
 */
public class Group implements Serializable {
    private int id ;
    private String groupName ;
    private int Uid          ;
    private int pos          ;

    public Group(String groupName, int uid, int pos) {
        this.groupName = groupName;
        this.Uid = uid;
        this.pos = pos;
    }

    public Group(int id, String groupName, int uid, int pos) {
        this.id = id;
        this.groupName = groupName;
        this.Uid = uid;
        this.pos = pos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getUid() {
        return Uid;
    }

    public void setUid(int uid) {
        Uid = uid;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }
}
