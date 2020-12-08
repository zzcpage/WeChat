package webqq.domain;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 未发送消息的消息类
 *  id 表示消息的id
 *  dates 表示发送消息的时刻
 */
public class UnSentMsg implements Serializable {
    private  int id ;
    private Date dates ;
    private int id1 ;
    private  int id2 ;
    private String message ;

    public UnSentMsg(Date dates, int id1, int id2, String message) {
        this.dates = dates;
        this.id1 = id1;
        this.id2 = id2;
        this.message = message;
    }

    public UnSentMsg(int id, Date dates, int id1, int id2, String message) {
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

    public void setDates(Date dates) {
        this.dates = dates;
    }

    public String getDates()
    {
        String value = null ;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        value = dateFormat.format(dates)  ;
        return value ;
    }
    public void setDates(String date)
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0) ; //从第一个位置开始解析
        try{
            dates = format.parse(date,pos) ;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
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
