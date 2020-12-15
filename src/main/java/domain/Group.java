package domain;

import java.io.Serializable;

/**
 * 分组类
 * id 表示分组序号
 * groupName 表示分组名称
 * Uid 表示分组所属的用户Uid
 * pos 表示属于用户的第几个分组(用户有多个分组，需要确定位置)
 */
public class Group implements Serializable {
    private Long id ;
    private String gname ;
    private Long uid;

    public Group() {
    }

    public Group(String gname, Long uid) {
        this.gname = gname;
        this.uid = uid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", gname='" + gname + '\'' +
                ", uid=" + uid +
                '}';
    }
}

