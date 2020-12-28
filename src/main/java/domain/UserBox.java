package domain;

import java.util.Date;

public class UserBox {
    private long uid ;
    private String uname ;
    private String account ;
    private Date birthday ;
    private String sex ;
    private String email;
    private String headimg ;
    private int state ;
    private String signature ;
    public UserBox(){}

    public UserBox(long uid, String uname, String account, Date birthday, String sex, String email, String headimg, int state, String signature) {
        this.uid = uid;
        this.uname = uname;
        this.account = account;
        this.birthday = birthday;
        this.sex = sex;
        this.email = email;
        this.headimg = headimg;
        this.state = state;
        this.signature = signature;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }


    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    @Override
    public String toString() {
        return "UserBox{" +
                "uid=" + uid +
                ", uname='" + uname + '\'' +
                ", account='" + account + '\'' +
                ", birthday=" + birthday +
                ", sex='" + sex + '\'' +
                ", email='" + email + '\'' +
                ", headimg='" + headimg + '\'' +
                ", state=" + state +
                ", signature='" + signature + '\'' +
                '}';
    }
}
