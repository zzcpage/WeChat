package domain;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;

/**
 * 用户类
 * Uid 数据库内部用户唯一标识
 * account 用户账号名
 * password 用户密码
 * sex 用户性别
 * birth 用户生日
 * signature 用户个性签名
 * bImage 背景图片
 * imgSrc 头像图片
 * groups 分组信息
 * friendlist 好友列表
 */
public class User implements Serializable {

    private Long uid ;
    private String uname;
    private String account ;
    private String password ;
    private String email;
    private String sex = "男";
    private Date birthday = new Date();
    private String signature = "";
    private String headimg = "";
    private int state = 0;

    public User() {
    }

    public User(String uname, String account, String password, String email, String sex, Date birthday, String signature, String headImg, int state) {
        this.uname = uname;
        this.account = account;
        this.password = password;
        this.sex = sex;
        this.birthday = birthday;
        this.email = email;
        this.signature = signature;
        this.headimg = headImg;
        this.state = state;
    }

    public User(String uname, String password, String email) {
        this.uname = uname;
        this.password = password;
        this.email = email;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", uname='" + uname + '\'' +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday=" + birthday +
                ", signature='" + signature + '\'' +
                ", headimg='" + headimg + '\'' +
                ", state=" + state +
                '}';
    }
}
