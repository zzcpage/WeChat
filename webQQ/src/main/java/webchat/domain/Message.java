package webchat.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * id 消息id
 * suid 发送者uid
 * ruid 接收者uid
 * date 发送时间
 * msg 消息内容
 * state 消息阅读状态
 */
public class Message implements Serializable {
    private long id ;
    private long suid ;
    private long ruid ;
    private Date date ;
    private String msg ;
    private int state ;
    public Message(){}
    public Message(long suid, long ruid, Date date, String msg, int state) {
        this.suid = suid;
        this.ruid = ruid;
        this.date = date;
        this.msg = msg;
        this.state = state;
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

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "message{" +
                "id=" + id +
                ", suid=" + suid +
                ", ruid=" + ruid +
                ", date=" + date +
                ", msg='" + msg + '\'' +
                ", state=" + state +
                '}';
    }
}
