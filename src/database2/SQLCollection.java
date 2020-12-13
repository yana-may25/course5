package database2;
import classes.JCollection;
import classes.Material;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class SQLCollection implements ICollection {
    private ConnectionDB dbConnection;
    private static SQLCollection instance;

    public SQLCollection() {
        dbConnection = ConnectionDB.getInstance();
    }

    public static synchronized SQLCollection getInstance() {
        if (instance == null) {
            instance = new SQLCollection();
        }
        return instance;
    }
    public ArrayList<JCollection> selectCollection() {

        String str = "SELECT * From collection";
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        ArrayList<JCollection> materials = new ArrayList<>();

        for (String[] items : result) {
            JCollection material = new JCollection();
            material.setId(Integer.parseInt(items[0]));
            material.setName(items[1]);
            material.setYear(Integer.parseInt(items[2]));
            material.setIndex(Double.parseDouble((items[3])));
            materials.add(material);
        }
        return materials;
    }

    public void add(JCollection collection) {
        try {
            String queryAdd = "INSERT into collection(name, year, index1) VALUES (?,?,?)";
            PreparedStatement st = dbConnection.getConnect().prepareStatement(queryAdd);
            st.setString(1, collection.getName());
            st.setInt(2, collection.getYear());
            st.setDouble(3, collection.getIndex());
            st.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public ArrayList<String> selectCollectionNames() throws SQLException {

        String query = "SELECT name From collection";

        ArrayList<String[]> result = dbConnection.getArrayResult(query);
        ArrayList<String> materials = new ArrayList<>();

        for (String[] items : result) {
            materials.add(items[0]);
        }
        return materials;
    }

    public void delete(JCollection material) {
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
