package webchat.domain;

import java.io.Serializable;
import java.sql.Date;

/**
 * id 请求编号
 * suid 发送者uid
 * ruid 接收者uid
 * date 请求时间
 * remark 备注
 * state 请求状态
 * sgroup 发送者在接收者的分组id
 * rgroup 接收者在发送者的分组id
 */
public class Request implements Serializable {
    private long id ;
    private long suid ;
    private long ruid ;
    private Date date ;
    private String remark ;
    private int state ;
    private long sgroup ;
    private long rgroup ;
    public Request(){}
    public Request(long suid, long ruid, Date date, String remark, int state, long rgroup) {
        this.suid = suid;
        this.ruid = ruid;
        this.date = date;
        this.remark = remark;
        this.state = state;
        this.rgroup = rgroup;
    }

    public Request(long suid, long ruid, Date date, String remark, int state, long sgroup, long rgroup) {
        this.suid = suid;
        this.ruid = ruid;
        this.date = date;
        this.remark = remark;
        this.state = state;
        this.sgroup = sgroup;
        this.rgroup = rgroup;
    }

    public Request(long id, long suid, long ruid, Date date, String remark, int state, long sgroup, long rgroup) {
        this.id = id;
        this.suid = suid;
        this.ruid = ruid;
        this.date = date;
        this.remark = remark;
        this.state = state;
        this.sgroup = sgroup;
        this.rgroup = rgroup;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public long getSgroup() {
        return sgroup;
    }

    public void setSgroup(long sgroup) {
        this.sgroup = sgroup;
    }

    public long getRgroup() {
        return rgroup;
    }

    public void setRgroup(long rgroup) {
        this.rgroup = rgroup;
    }

    @Override
    public String toString() {
        return "request{" +
                "id=" + id +
                ", suid=" + suid +
                ", ruid=" + ruid +
                ", date=" + date +
                ", remark='" + remark + '\'' +
                ", state=" + state +
                ", sgroup=" + sgroup +
                ", rgroup=" + rgroup +
                '}';
    }
}
