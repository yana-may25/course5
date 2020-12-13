package database2;

import classes.JCollection;
import classes.Material;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SQLMaterial implements IMaterials{
    private ConnectionDB dbConnection;
    private static SQLMaterial instance;

    public SQLMaterial() {
        dbConnection = ConnectionDB.getInstance();
    }

    public static synchronized SQLMaterial getInstance() {
        if (instance == null) {
            instance = new SQLMaterial();
        }
        return instance;
    }
    public ArrayList<Material> selectMaterials() {
//
        String str = "SELECT * From material";
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        ArrayList<Material> materials = new ArrayList<>();

        for (String[] items : result) {
            Material material = new Material();
            material.setId(Integer.parseInt(items[0]));
            material.setName(items[1]);
            material.setType(items[2]);
            material.setValue(Double.parseDouble((items[3])));
            materials.add(material);
        }
        return materials;
    }
    public ArrayList<String> selectMaterialNames(String a) throws SQLException {

        String str = "SELECT name From material  Where type = '"+ a +"'";

        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        ArrayList<String> jewName = new ArrayList<>();

        for (String[] items : result) {
            jewName.add(items[0]);
        }
        return jewName;
    }

    public void add(Material material) {
        try {
            String queryAdd = "INSERT into material (name, type, value) VALUES (?,?,?)";
            PreparedStatement st = dbConnection.getConnect().prepareStatement(queryAdd);
            st.setString(1, material.getName());
            st.setString(2, material.getType());
            st.setDouble(3, material.getValue());
            st.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void delete(Material material) {
        try {
            String REMOVE_GROUP = "DELETE FROM material " + "WHERE idmaterial = ?";
            PreparedStatement statement = dbConnection.getConnect().prepareStatement(REMOVE_GROUP);
            statement.setInt(1, material.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateValue (double value, int id) {
        try {
            String UPDATE_NAME = "UPDATE material " + "SET value = ?" +"WHERE idmaterial = ?";
            PreparedStatement statement = dbConnection.getConnect().prepareStatement(UPDATE_NAME);
            statement.setDouble(1, value);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
