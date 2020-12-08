package webqq.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 好友消息类
 * id表示消息的序号
 * dates表示消息的事件
 * id1表示消息的发送方id
 * id2表示消息的接收方id
 */
public class Msg implements Serializable {
    private int id ;
    private Date dates ;
    private int id1 ;
    private int id2 ;
    private  String message;

    public Msg(Date dates, int id1, int id2, String message) {
        this.dates = dates;
        this.id1 = id1;
        this.id2 = id2;
        this.message = message;
    }

    public Msg(int id, Date dates, int id1, int id2, String message) {
        this.id = id;
        this.dates = dates;
        this.id1 = id1;
        this.id2 = id2;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDates() {
        return dates;
    }

    public void setDates(Date dates) {
        this.dates = dates;
    }

    public int getId1() {
        return id1;
    }

    public void setId1(int id1) {
        this.id1 = id1;
    }

    public int getId2() {
        return id2;
    }

    public void setId2(int id2) {
        this.id2 = id2;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
