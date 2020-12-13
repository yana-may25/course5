package database2;

import classes.Jewellery;
import classes.Purchase;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class SQLPurchase implements IPurchase {

    private ConnectionDB dbConnection;
    private static SQLPurchase instance;

    public SQLPurchase() {
        dbConnection = ConnectionDB.getInstance();
    }

    public static synchronized SQLPurchase getInstance() {
        if (instance == null) {
            instance = new SQLPurchase();
        }
        return instance;
    }

    public ArrayList<String> checkClientTel(String clientTel) {
        try {
            String query = "SELECT idClient From clientCard Where phone_num ='" + clientTel + "'";
            PreparedStatement statement = dbConnection.getConnect().prepareStatement(query);
            ArrayList<String[]> result = dbConnection.getArrayResult(query);
            ArrayList<String> materials = new ArrayList<>();
            for (String[] items : result) {
                materials.add(items[0]);
            }
            String query2 = "SELECT rate\n" +
                    "FROM   discounts\n" +
                    "where purch_num <=(\n" +
                    "select count(idorder)\n" +
                    " from purchase\n" +
                    " inner join clientcard on purchase.idclient=clientcard.idClient\n" +
                    " where clientcard.phone_num='" +clientTel+"'"+" "+
                    " group by purchase.idclient)\n" +
                    "order by rate desc\n" +
                    "limit 1";
//            statement1 = dbConnection.getConnect().prepareStatement(query2);
            ArrayList<String[]> result1 = dbConnection.getArrayResult(query2);
            for (String[] items : result1) {
                materials.add(items[0]);
            }
            return materials;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setDiscount(int idclient) {
        try {

            String REMOVE_GROUP = "SELECT rate\n" +
                    "FROM   discounts\n" +
                    "where purch_num <=(\n" +
                    "select count(idorder)\n" +
                    "from purchase\n" +
                    "where idclient = 1\n" +
                    "group by idclient)\n" +
                    "order by rate desc\n" +
                    "limit 1\n";
            PreparedStatement statement = dbConnection.getConnect().prepareStatement(REMOVE_GROUP);
            statement.setInt(1, idclient);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public double selectPrice(String name) {
        String query = "SELECT price From jewellery Where name ='" + name +"'";
        ArrayList<String[]> result = dbConnection.getArrayResult(query);
        double price = 0;
        for (String[] items : result) {

            price = Double.parseDouble(items[0]);

        }
        return price;
    }

    public void setPurchases(ArrayList<String> purchaseNames, String idClient) throws SQLException {
        ArrayList<String> materials = new ArrayList<>();
        int newIdOrder = 0;

        String query2 = "SELECT idorder FROM purchase ORDER BY idorder DESC LIMIT 1 ";
        ArrayList<String[]> result2 = dbConnection.getArrayResult(query2);
        for (String[] items : result2) {
            newIdOrder = Integer.parseInt(items[0]) + 1;
        }

        for (int i = 0; i < purchaseNames.size(); i++) {

            String query = "SELECT idjewellery From jewellery Where name = '" +
                    purchaseNames.get(i) + "'";
            ArrayList<String[]> result = dbConnection.getArrayResult(query);
            for (String[] items : result) {
                materials.add(items[0]);

            }
            //---вставляем в бд

            String query3 = "INSERT INTO purchase(idorder, idjewellery, idclient) VALUES (?,?,?) ";
            PreparedStatement st = dbConnection.getConnect().prepareStatement(query3);
            st.setInt(1, newIdOrder);
            st.setInt(2, Integer.parseInt(materials.get(materials.size() - 1)));
            st.setString(3, idClient);
            st.executeUpdate();

        }
    }
    //---------- Statistics ----------

    public ArrayList<String[]> showItemsNumber() {

        String query2 = "select idorder, count(purchase.idjewellery), idclient, sum(price)\n" +
                "from purchase\n" +
                "inner join jewellery on purchase.idjewellery=jewellery.idjewellery\n" +
                "group by purchase.idorder\n" +
                "order by  count(purchase.idjewellery) DESC";

        ArrayList<String[]> result2 = dbConnection.getArrayResult(query2);
        return result2;
    }

    public ArrayList<String[]> showOrdersNumber() {

        String query2 = "select purchase.idclient, count(idorder), sum(price)\n" +
                "from purchase\n" +
                "inner join jewellery on purchase.idjewellery=jewellery.idjewellery\n" +
                "group by purchase.idclient";

        ArrayList<String[]> result2 = dbConnection.getArrayResult(query2);
        return result2;
    }

    public ArrayList<Jewellery> itemsPopularity() {

        String query2 = "select count(purchase.idjewellery), jewellery.name\n" +
                "from purchase\n" +
                "inner join jewellery on jewellery.idjewellery = purchase.idjewellery\n" +
                "group by purchase.idjewellery";

        ArrayList<String[]> result = dbConnection.getArrayResult(query2);
        ArrayList<Jewellery> result2 = new ArrayList<>();
        for (String[] items : result) {
            Jewellery statistics = new Jewellery();
            statistics.setId(Integer.parseInt(items[0]));
            statistics.setName(items[1]);
            result2.add(statistics);
        }
        return result2;

    }
}
