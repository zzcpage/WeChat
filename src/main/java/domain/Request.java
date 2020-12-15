package domain;

import java.io.Serializable;
import java.util.Date;

/**
 * id 结果列表序号
 * uid 表示用户1
 * uid2 表示用户2
 * type 表示处理结果 0 表示正在处理(初始值为0) 1 表示同意 2表示拒绝
 * pos1 表示用户2 在用户1 的分组
 * pos2 表示用户1 在用户2 的分组 可以为空(未同意和拒绝时候)
 * date 表示处理的时间
 */
public class Request implements Serializable {

    private Long id;
    private Long suid;
    private Long ruid;
    private String remark;
    private Integer state;
    //发送者在请求者的分组
    private Long sgroup;
    //接收者在发送者的分组
    private Long rgroup;
    private Date date ;

    public Request() {
    }

    public Request(Long id, Long suid, Long ruid, String remark, Integer state, Long sGroup, Long rGroup, Date date) {
        this.id = id;
        this.suid = suid;
        this.ruid = ruid;
        this.remark = remark;
        this.state = state;
        this.sgroup = sGroup;
        this.rgroup = rGroup;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Long getSgroup() {
        return sgroup;
    }

    public void setSgroup(Long sgroup) {
        this.sgroup = sgroup;
    }

    public Long getRgroup() {
        return rgroup;
    }

    public void setRgroup(Long rgroup) {
        this.rgroup = rgroup;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", suid=" + suid +
                ", ruid=" + ruid +
                ", remark='" + remark + '\'' +
                ", state=" + state +
                ", sgroup=" + sgroup +
                ", rgroup=" + rgroup +
                ", date=" + date +
                '}';
    }
}

