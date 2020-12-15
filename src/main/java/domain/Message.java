package domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 好友消息类
 * id表示消息的序号
 * dates表示消息的事件
 * id1表示消息的发送方id
 * id2表示消息的接收方id
 */
public class Message implements Serializable {

    private Long id;
    private Date date;
    private Long suid;
    private Long ruid;
    private Integer state;
    private  String msg;

    public Message() {
    }

    public Message(Long suid, Long ruid, Integer state, String msg, Date date) {
        this.date = date;
        this.suid = suid;
        this.ruid = ruid;
        this.state = state;
        this.msg = msg;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getSuid() {
        return suid;
    }

    public void setSuid(Long suid) {
        this.suid = suid;
    }

    public Long getRuid() {
        return ruid;
    }

    public void setRuid(Long ruid) {
        this.ruid = ruid;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", date=" + date +
                ", suid=" + suid +
                ", ruid=" + ruid +
                ", state=" + state +
                ", msg='" + msg + '\'' +
                '}';
    }
}
