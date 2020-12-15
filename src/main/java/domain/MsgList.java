package domain;

import java.util.Date;

public class MsgList {
    private Long id;
    private Long suid;
    private Long ruid;
    private String msg;
    private Date date;

    public MsgList() {
    }

    public MsgList(Long suid, Long ruid, String msg, Date date) {
        this.suid = suid;
        this.ruid = ruid;
        this.msg = msg;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "MsgList{" +
                "id=" + id +
                ", suid=" + suid +
                ", ruid=" + ruid +
                ", msg='" + msg + '\'' +
                ", date=" + date +
                '}';
    }
}
