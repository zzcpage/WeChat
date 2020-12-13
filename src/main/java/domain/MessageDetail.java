package domain;

import java.util.Date;

public class MessageDetail {
    private Long id;
    private Date date;
    private Long suid;
    private Long ruid;
    private Integer state;
    private  String message;
    private String imgSrc;
    //发送方用户名
    private String sName;

    public MessageDetail(Date date, Long suid, Long ruid, Integer state, String message, String imgSrc, String sName) {
        this.date = date;
        this.suid = suid;
        this.ruid = ruid;
        this.state = state;
        this.message = message;
        this.imgSrc = imgSrc;
        this.sName = sName;
    }

    public MessageDetail(Message message, String sName, String imgSrc) {
        this.date = message.getDate();
        this.suid = message.getSuid();
        this.ruid = message.getRuid();
        this.state = message.getState();
        this.message = message.getMessage();
        this.imgSrc = imgSrc;
        this.sName = sName;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }
}
