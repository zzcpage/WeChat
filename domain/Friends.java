package webqq.domain;

import java.io.Serializable;

/**
 * 好友类
 * id1 表示好友1
 * id2 表示好友2
 * group1 表示好友2 在好友1 的位置,与group的id对应
 * group2 表示好友1 在好友2 的位置,与group的id对应
 */
public class Friends implements Serializable {
    private int id ;
    private int id1 ;
    private int id2 ;
    private int group1 ;
    private int group2 ;

    public Friends(int id, int id1, int id2, int group1, int group2) {
        this.id = id;
        this.id1 = id1;
        this.id2 = id2;
        this.group1 = group1;
        this.group2 = group2;
    }

    public Friends(int id1, int id2, int group1, int group2) {
        this.id1 = id1;
        this.id2 = id2;
        this.group1 = group1;
        this.group2 = group2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getGroup1() {
        return group1;
    }

    public void setGroup1(int group1) {
        this.group1 = group1;
    }

    public int getGroup2() {
        return group2;
    }

    public void setGroup2(int group2) {
        this.group2 = group2;
    }
}
