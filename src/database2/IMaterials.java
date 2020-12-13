package database2;

import classes.Material;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IMaterials {
    public ArrayList<Material> selectMaterials();
    public ArrayList<String> selectMaterialNames(String a) throws SQLException;
    public void add(Material material);
    public void delete(Material material);
    public void updateValue (double value, int id);

}
