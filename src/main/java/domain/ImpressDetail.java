package domain;

import java.util.Date;

public class ImpressDetail {
    private Long id;
    private Long suid;
    private Date date;
    private String msg;
    private String uname;
    private String imgSrc;

    public ImpressDetail(Impress impress, String uname, String imgSrc) {
        this.msg = impress.getMsg();
        this.id = impress.getId();
        this.suid = impress.getSuid();
        this.date = impress.getDate();
        this.imgSrc = imgSrc;
        this.uname = uname;
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

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    @Override
    public String toString() {
        return "ImpressDetail{" +
                "id=" + id +
                ", suid=" + suid +
                ", date=" + date +
                ", msg='" + msg + '\'' +
                ", uname='" + uname + '\'' +
                ", imgSrc='" + imgSrc + '\'' +
                '}';
    }
}
