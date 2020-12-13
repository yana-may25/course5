package database2;

import classes.Discount;
import classes.JCollection;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class SQLDiscount implements IDiscount {

    private ConnectionDB dbConnection;
    private static SQLDiscount instance;

    public SQLDiscount() {
        dbConnection = ConnectionDB.getInstance();
    }

    public static synchronized SQLDiscount getInstance() {
        if (instance == null) {
            instance = new SQLDiscount();
        }
        return instance;
    }

    public ArrayList<Discount> selectDiscounts(){
        String str = "SELECT * From discounts";
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        ArrayList<Discount> materials = new ArrayList<>();

        for (String[] items : result) {
            Discount material = new Discount();
            material.setPurchNum(Integer.parseInt(items[1]));
            material.setRate((items[2]));
            materials.add(material);
        }
        return materials;
    }

    public void updateValue (int rate, int purchNum) {
        try {
            String UPDATE_NAME = "UPDATE discounts " + "SET rate = ? " +"WHERE purch_num = ?";
            PreparedStatement statement = dbConnection.getConnect().prepareStatement(UPDATE_NAME);
            statement.setInt(1, rate);
            statement.setInt(2, purchNum);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
