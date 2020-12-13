package database2;

import classes.Jewellery;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IPurchase {

    public ArrayList<String> checkClientTel(String clientTel);
    public void setDiscount(int idclient);
    public double selectPrice(String name);
    public void setPurchases(ArrayList<String> purchaseNames, String idClient) throws SQLException;
    public ArrayList<String[]> showItemsNumber();
    public ArrayList<String[]> showOrdersNumber();
    public ArrayList<Jewellery> itemsPopularity();
}
