package domain;

import java.util.Date;

public class RequestMessage {
    private Long id;
    private Long suid;
    private String remark;
    private Integer state;
    //发送者在接收者分组
    private Long sGroup;
    private Date dates;
    private String imgSrc;
    private String uname;

    public RequestMessage(Long suid, String remark, Integer state, Long sGroup, Date dates, String imgSrc, String uname) {
        this.suid = suid;
        this.remark = remark;
        this.state = state;
        this.sGroup = sGroup;
        this.dates = dates;
        this.imgSrc = imgSrc;
        this.uname = uname;
    }

    public RequestMessage(Request request, String uname, String imgSrc) {
        this.id = request.getId();
        this.dates = request.getDate();
        this.sGroup = request.getSgroup();
        this.imgSrc = imgSrc;
        this.uname = uname;
        this.remark = request.getRemark();
        this.suid = request.getSuid();
        this.state = request.getState();
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

    public Long getsGroup() {
        return sGroup;
    }

    public void setsGroup(Long sGroup) {
        this.sGroup = sGroup;
    }

    public Date getDates() {
        return dates;
    }

    public void setDates(Date dates) {
        this.dates = dates;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    @Override
    public String toString() {
        return "RequestMessage{" +
                "id=" + id +
                ", suid=" + suid +
                ", remark='" + remark + '\'' +
                ", state=" + state +
                ", sGroup=" + sGroup +
                ", dates=" + dates +
                ", imgSrc='" + imgSrc + '\'' +
                ", uname='" + uname + '\'' +
                '}';
    }
}
