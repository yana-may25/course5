package database2;

import classes.Jewellery;

import java.util.ArrayList;

public interface IJewellery {
    public ArrayList<Jewellery> selectAll();
    public String add(Jewellery user);
    public void delete(Jewellery user);
    public ArrayList<String> selectNames();

}
