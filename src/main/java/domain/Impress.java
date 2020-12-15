package domain;

import java.util.Date;

public class Impress {
    private Long id;
    private Long suid;
    private Long ruid;
    private Date date;
    private String msg;

    public Impress() {
    }

    public Impress(Long suid, Long ruid, Date date, String msg) {
        this.suid = suid;
        this.ruid = ruid;
        this.date = date;
        this.msg = msg;
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
        return "Impress{" +
                "id=" + id +
                ", suid=" + suid +
                ", ruid=" + ruid +
                ", date=" + date +
                ", msg='" + msg + '\'' +
                '}';
    }
}
