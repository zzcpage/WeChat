package webchat.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * id 消息列表序号
 * suid 发送者uid
 * ruid 接收者uid
 */
public class MsgList implements Serializable {
    private long id ;
    private long suid;
    private long ruid;
    private Date date ;
    private String msg ;
    public MsgList(){}
    public MsgList(long suid, long ruid) {
        this.suid = suid;
        this.ruid = ruid;
    }

    public MsgList(long suid, long ruid, Date date, String msg) {
        this.suid = suid;
        this.ruid = ruid;
        this.date = date;
        this.msg = msg;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSuid() {
        return suid;
    }

    public void setSuid(long suid) {
        this.suid = suid;
    }

    public long getRuid() {
        return ruid;
    }

    public void setRuid(long ruid) {
        this.ruid = ruid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Msglist{" +
                "id=" + id +
                ", suid=" + suid +
                ", ruid=" + ruid +
                ", date=" + date +
                ", msg='" + msg + '\'' +
                '}';
    }
}
