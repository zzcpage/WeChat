package webqq.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * id  请求列表序号
 * uid 请求发送者uid
 * uid2 请求接收者uid
 * pos 所处分组的位置
 * dates 表示发送请求的时间
 */
public class RequestFriend implements Serializable {
        private int id  ;
        private int uid ;
        private int uid2 ;
        private int pos ;
        private Date dates ;
        public RequestFriend(int uid, int pos) {
            this.uid = uid;
            this.pos = pos;
        }

        public RequestFriend(int id, int uid, int pos) {
            this.id = id;
            this.uid = uid;
            this.pos = pos;
        }

        public RequestFriend(int id, int uid, int pos, Date dates) {
            this.id = id;
            this.uid = uid;
            this.pos = pos;
            this.dates = dates;
        }

        public RequestFriend(int uid, int pos, Date dates) {
            this.uid = uid;
            this.pos = pos;
            this.dates = dates;
        }

        public Date getDates() {
                return dates;
        }

        public void setDates(Date dates) {
            this.dates = dates;
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

        public int getPos() {
            return pos;
        }

        public void setPos(int pos) {
            this.pos = pos;
        }
}
