package webqq.domain;

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
public class Result implements Serializable {
    private int id   ;
    private int uid  ;
    private int uid2 ;
    private int type ;
    private int pos1 ;
    private int pos2 ;
    private int isRead ;
    private Date dates ;

    public Result(int uid, int uid2, int type, int pos1, int pos2, int isRead, Date dates) {
        this.uid = uid;
        this.uid2 = uid2;
        this.type = type;
        this.pos1 = pos1;
        this.pos2 = pos2;
        this.isRead = isRead;
        this.dates = dates;
    }

    public Result(int id, int uid, int uid2, int type, int pos1, int pos2, int isRead, Date dates) {
        this.id = id;
        this.uid = uid;
        this.uid2 = uid2;
        this.type = type;
        this.pos1 = pos1;
        this.pos2 = pos2;
        this.isRead = isRead;
        this.dates = dates;
    }

    public Result(int uid, int uid2, int type, int pos1, int isRead, Date dates) {
        this.uid = uid;
        this.uid2 = uid2;
        this.type = type;
        this.pos1 = pos1;
        this.isRead = isRead;
        this.dates = dates;
    }

    public Date getDates() {
        return dates;
    }

    public void setDates(Date dates) {
        this.dates = dates;
    }

    public int getIsRead() {
        return isRead;
    }

    public void setIsRead(int isRead) {
        this.isRead = isRead;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getUid2() {
        return uid2;
    }

    public void setUid2(int uid2) {
        this.uid2 = uid2;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getPos1() {
        return pos1;
    }

    public void setPos1(int pos1) {
        this.pos1 = pos1;
    }

    public int getPos2() {
        return pos2;
    }

    public void setPos2(int pos2) {
        this.pos2 = pos2;
    }
}
