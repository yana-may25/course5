package database2;

import classes.Jewellery;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class SQLJewellery implements IJewellery{
    private ConnectionDB dbConnection;
    private static SQLJewellery instance;

    public SQLJewellery() {
        dbConnection = ConnectionDB.getInstance();
    }

    public static synchronized SQLJewellery getInstance() {
        if (instance == null) {
            instance = new SQLJewellery();
        }
        return instance;
    }

    public ArrayList<String> selectNames() {
        String str = "SELECT name From jewellery";
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        ArrayList<String> jewName = new ArrayList<>();

        for (String[] items : result) {
            jewName.add(items[0]);
        }
        return jewName;
    }

    public ArrayList<Jewellery> selectAll() {
        String str = "SELECT * From jewellery";
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        ArrayList<Jewellery> jewName = new ArrayList<>();

        for (String[] items : result) {
            Jewellery jew = new Jewellery();
            jew.setId(Integer.parseInt(items[0]));
            jew.setType(items[1]);
            jew.setCollection(items[2]);
            jew.setMaterial1(items[3]);
            jew.setWeight1(Double.parseDouble(items[4]));
            jew.setMaterial2(items[5]);
            jew.setWeight2(Double.parseDouble(items[6]));
            jew.setPrice(Double.parseDouble(items[7]));
            jew.setName(items[8]);
            jewName.add(jew);
        }
        return jewName;
    }

    public double calculate(String str1, Double w1, String str2, Double w2, String collection) {
        Double newPrice;
        String query3 = "SELECT index1 From collection WHERE name = '" + collection + "'";
        ArrayList<String[]> result3 = dbConnection.getArrayResult(query3);
        ArrayList<Double> mName = new ArrayList<>();

        for (String[] items : result3) {
            mName.add(Double.valueOf(items[0]));
        }

        String query = "SELECT value From material WHERE name = '" + str1 + "'";
        result3 = dbConnection.getArrayResult(query);
        //ArrayList<String> jewName1 = new ArrayList<>();
        for (String[] items : result3) {
            mName.add(Double.valueOf(items[0]));
        }
        if (str2 != "none") {
            String query2 = "SELECT value From material WHERE name ='" + str1 + "'";
            result3 = dbConnection.getArrayResult(query2);
            for (String[] items : result3) {
                mName.add(Double.valueOf(items[0]));
            }

            newPrice = mName.get(0) * (mName.get(1) * w1 + mName.get(2) * w2);
        } else
            newPrice = mName.get(0) * (mName.get(1) * w1);


        return newPrice;
    }

    public String add(Jewellery user) {

        String queryGetMaxId = "SELECT max(idjewellery) FROM course5.jewellery";
        ArrayList<String[]> result3 = dbConnection.getArrayResult(queryGetMaxId);
        int newId = 0;
        for (String[] items : result3) {
            newId = Integer.parseInt((items[0]));
        }
        user.setId(newId);


        try {
            String queryAdd = "INSERT into jewellery (type, material1,weight1,material2," +
                    "weight2, price, collection, name) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement st = dbConnection.getConnect().prepareStatement(queryAdd);
            st.setString(1, user.getType());
            st.setString(2, user.getMaterial1());
            st.setDouble(3, user.getWeight1());
            st.setString(4, user.getMaterial2());
            st.setDouble(5, user.getWeight2());
            st.setDouble(6, user.getPrice());
            st.setString(7, user.getCollection());
            st.setString(8, user.toString());
            st.executeUpdate();
            return user.toString();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public void delete(Jewellery jewellery) {
        try {

            String REMOVE_GROUP = "DELETE FROM collection " + "WHERE idcollection = ?";
            PreparedStatement statement = dbConnection.getConnect().prepareStatement(REMOVE_GROUP);
            statement.setInt(1, jewellery.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
