package stats;

import java.io.Serializable;

public class Statistics1 implements Serializable {
    private int idOrder;
    private int jewNumber;
    private double sum;

    public Statistics1(int idOrder, int jewNumber, double sum) {
        this.idOrder = idOrder;
        this.jewNumber = jewNumber;
        this.sum = sum;
    }

    public Statistics1() {
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public int getJewNumber() {
        return jewNumber;
    }

    public void setJewNumber(int jewNumber) {
        this.jewNumber = jewNumber;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

}