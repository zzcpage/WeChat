package webchat.domain;

import java.io.Serializable;
import java.sql.Date;

/**
 * 用户类
 * uid 用户内部唯一标识
 * uname 用户昵称
 * account 用户账号
 * password 用户密码
 * sex 用户性别
 * email 用户邮箱
 * heading 用户头像
 * state 用户登录状态
 * signature 用户签名
 */
public class User implements Serializable {
    private long uid ;
    private String uname ;
    private String account ;
    private String password ;
    private Date birthday ;
    private String sex ;
    private String email;
    private String headimg ;
    private int state ;
    private String signature ;
    public User(){}
    public User(String uname, String account, String password, Date birthday, String sex, String email, String headimg, int state) {
        this.uname = uname;
        this.account = account;
        this.password = password;
        this.birthday = birthday;
        this.sex = sex;
        this.email = email;
        this.headimg = headimg;
        this.state = state;
    }

    public User(String uname, String account, String password, Date birthday, String sex, String email, String headimg, int state, String signature) {
        this.uname = uname;
        this.account = account;
        this.password = password;
        this.birthday = birthday;
        this.sex = sex;
        this.email = email;
        this.headimg = headimg;
        this.state = state;
        this.signature = signature;
    }

    public User(long uid, String uname, String account, String password, Date birthday, String sex, String email, String headimg, int state, String signature) {
        this.uid = uid;
        this.uname = uname;
        this.account = account;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        return "user{" +
                "uid=" + uid +
                ", uname='" + uname + '\'' +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", birthday=" + birthday +
                ", sex='" + sex + '\'' +
                ", email='" + email + '\'' +
                ", headimg='" + headimg + '\'' +
                ", state=" + state +
                ", signature='" + signature + '\'' +
                '}';
    }
}
