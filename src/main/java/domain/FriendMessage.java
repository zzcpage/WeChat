package domain;


import java.util.Date;

public class FriendMessage {
    //uid
    private Long uid;
    //账号
    private String account ;
    //用户名
    private String uname;
    //性别
    private String sex ;
    //生日
    private Date birth ;
    //签名
    private String signature;
    //头像
    private String imgSrc;
    //是否在线
    private Integer status;

    public FriendMessage() {
    }

    public FriendMessage(Long uid, String account, String uname, String sex, Date birth, String signature, String imgSrc, Integer status) {
        this.uid = uid;
        this.account = account;
        this.uname = uname;
        this.sex = sex;
        this.birth = birth;
        this.signature = signature;
        this.imgSrc = imgSrc;
        this.status = status;
    }

    public FriendMessage(User user) {
        this.uid = user.getUid();
        this.account = user.getAccount();
        this.birth = user.getBirthday();
        this.imgSrc = user.getHeadImg();
        this.signature = user.getSignature();
        this.sex = user.getSex();
        this.uname = user.getUname();
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

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
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
}
