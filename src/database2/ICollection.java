package database2;

import classes.JCollection;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ICollection {

    public void add(JCollection user);
    public void delete(JCollection user);
    public ArrayList<String> selectCollectionNames() throws SQLException;
    public void updateValue (double value, int id);
}
