package webchat.domain;

import java.io.Serializable;
import java.util.Date ;

/**
 * id 映像id
 * suid 写映像的用户
 * ruid 被写映像的用户
 * date 写印象的日期
 * msg 印象内容
 */
public class Impress implements Serializable {
    private long id ;
    private long suid ;
    private long ruid ;
    private Date date ;
    private String msg ;
    public Impress(){}
    public Impress(long suid, long ruid, Date date, String msg) {
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
        return "impress{" +
                "id=" + id +
                ", suid=" + suid +
                ", ruid=" + ruid +
                ", date=" + date +
                ", msg='" + msg + '\'' +
                '}';
    }
}
