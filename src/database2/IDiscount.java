package database2;

import classes.Discount;

import java.util.ArrayList;

public interface IDiscount {
    public ArrayList<Discount> selectDiscounts();
    public void updateValue (int rate, int purchNum);
}
