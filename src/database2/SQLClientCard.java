package database2;

import classes.ClientCard;
import classes.JCollection;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class SQLClientCard  implements IClientCard{

    private ConnectionDB dbConnection;
    private static SQLClientCard instance;

    public SQLClientCard() {
        dbConnection = ConnectionDB.getInstance();
    }

    public static synchronized SQLClientCard getInstance() {
        if (instance == null) {
            instance = new SQLClientCard();
        }
        return instance;
    }
    public ArrayList<ClientCard> selectCollection() {
//
        String str = "select  clientcard.idClient, clientcard.phone_num, name, surname, count(idorder)\n" +
                " from purchase\n" +
                " inner join clientcard on purchase.idclient=clientcard.idClient\n" +
                " group by purchase.idclient";
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        ArrayList<ClientCard> materials = new ArrayList<>();

        for (String[] items : result) {
            ClientCard material = new ClientCard();
            material.setId(Integer.parseInt(items[0]));
            material.setNumber(items[1]);
            material.setName((items[2]));
            material.setSurname(items[3]);
            material.setPurchases(Integer.parseInt(items[4]));
//            material.setDiscount(Integer.parseInt(items[5]));
            materials.add(material);
        }
        return materials;
    }

    public void add(ClientCard collection) {
        try {
            String queryAdd = "INSERT into clientcard(phone_num, " +
                    "name, surname) VALUES (?,?,?)";
            PreparedStatement st = dbConnection.getConnect().prepareStatement(queryAdd);
            st.setString(1, collection.getNumber());
            st.setString(2, collection.getName());
            st.setString(3, collection.getSurname());
//            st.setInt(4, collection.getPurchases());
//            st.setInt(5, collection.getDiscount());


            st.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void delete(ClientCard material) {
        try {
            String REMOVE_GROUP = "DELETE FROM collection " + "WHERE idcollection = ?";
            PreparedStatement statement = dbConnection.getConnect().prepareStatement(REMOVE_GROUP);
            statement.setInt(1, material.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateValue (double value, int id) {
        try {
            String UPDATE_NAME = "UPDATE collection " + "SET index1 = ?" +"WHERE idcollection = ?";
            PreparedStatement statement = dbConnection.getConnect().prepareStatement(UPDATE_NAME);
            statement.setDouble(1, value);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
