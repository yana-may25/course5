package classes;

import java.io.Serializable;

public class Discount implements Serializable {
    private int id;
    private int purchNum;
    private String rate;

    public Discount(int purchNum, String rate) {
        this.purchNum = purchNum;
        this.rate = rate;
    }

    public Discount() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPurchNum() {
        return purchNum;
    }

    public void setPurchNum(int purchNum) {
        this.purchNum = purchNum;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}
