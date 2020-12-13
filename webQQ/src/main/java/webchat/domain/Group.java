package webchat.domain;

import java.io.Serializable;

/**
 * id 分组序号
 * uid 分组所属用户
 * gname 分组名称
 */
public class Group implements Serializable {
    private long id ;
    private long uid ;
    private String gname ;
    public Group(){}
    public Group(long uid, String gname) {
        this.uid = uid;
        this.gname = gname;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    @Override
    public String toString() {
        return "group{" +
                "id=" + id +
                ", uid=" + uid +
                ", gname='" + gname + '\'' +
                '}';
    }
}
