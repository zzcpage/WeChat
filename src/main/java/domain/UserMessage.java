package domain;

import java.sql.Date;
import java.util.List;

public class UserMessage {

    private Long uid ;
    private String account ;
    private String uName;
    private String sex ;
    private Date birthday ;
    private String signature ;
    private String imgSrc ;
    private Integer status ;
    private List<GroupMessage> groups;

    public UserMessage() {

    }

    public UserMessage(String account, String sex, Date birthday, String signature, String imgSrc, Integer status, List<GroupMessage> groups) {
        this.account = account;
        this.sex = sex;
        this.birthday = birthday;
        this.signature = signature;
        this.imgSrc = imgSrc;
        this.status = status;
        this.groups = groups;
    }

    public UserMessage(User user, List<GroupMessage> groups) {
        this.groups = groups;
        this.uid = user.getUid();
        this.account = user.getAccount();
        this.signature = user.getSignature();
        this.uName = user.getUname();
        this.sex = user.getSex();
        this.birthday = user.getBirthday();
        this.imgSrc = user.getHeadImg();
        this.status = user.getStatus();
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<GroupMessage> getGroups() {
        return groups;
    }

    public void setGroups(List<GroupMessage> groups) {
        this.groups = groups;
    }
}
