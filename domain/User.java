package webqq.domain;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;

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
    private int Uid ;
    private int account ;
    private String password ;
    private int sex ;
    private Date birth ;
    private String signature ;
    private String bImage ;
    private String imgSrc ;
    private int status ;
    private ArrayList<group> groups ;
    private ArrayList<friends> friendList;
    public User(int amount, String password) {
        this.account = amount;
        this.password = password;
    }
    public User(int account, String password, int sex, Date birth, String bImage, String imgSrc) {
        this.account = account;
        this.password = password;
        this.sex = sex;
        this.birth = birth;
        this.bImage = bImage;
        this.imgSrc = imgSrc;
    }

    public User(int account, String password, int sex, Date birth, String signature, String bImage, String imgSrc, int status) {
        this.account = account;
        this.password = password;
        this.sex = sex;
        this.birth = birth;
        this.signature = signature;
        this.bImage = bImage;
        this.imgSrc = imgSrc;
        this.status = status;
    }

    public User(int amount, String password, int sex, Date birth) {
        this.account = amount;
        this.password = password;
        this.sex = sex;
        this.birth = birth;
    }

    public User(int uid, int amount, String password, int sex, Date birth, String signature) {
        Uid = uid;
        this.account = amount;
        this.password = password;
        this.sex = sex;
        this.birth = birth;
        this.signature = signature;
    }

    public ArrayList<friends> getFriendList() {
        return friendList;
    }

    public void setFriendList(ArrayList<friends> friendList) {
        this.friendList = friendList;
    }

    public int getUid() {
        return Uid;
    }

    public void setUid(int uid) {
        Uid = uid;
    }

    public int getAccount() {
        return account;
    }

    public void setAccount(int account) {
        this.account = account;
    }

    public int getAmount() {
        return account;
    }

    public void setAmount(int amount) {
        this.account = amount;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
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

    public ArrayList<group> getGroups() {
        return groups;
    }

    public void setGroups(ArrayList<group> groups) {
        this.groups = groups;
    }
    public String getbImage() {
        return bImage;
    }

    public void setbImage(String bImage) {
        this.bImage = bImage;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
