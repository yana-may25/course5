package stats;

import java.io.Serializable;

public class Statistics2 implements Serializable {
    int clientNum;
    String orderNum;

    public Statistics2(int clientNum, String orderNum) {
        this.clientNum = clientNum;
        this.orderNum = orderNum;
    }

    public Statistics2() {
    }

    public int getClientNum() {
        return clientNum;
    }

    public void setClientNum(int clientNum) {
        this.clientNum = clientNum;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }
}
