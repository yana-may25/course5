package database2;

import classes.ClientCard;

import java.util.ArrayList;

public interface IClientCard {
    public ArrayList<ClientCard> selectCollection();
    public void add(ClientCard user);
    public void delete(ClientCard user);
    public void updateValue (double value, int id);
}
