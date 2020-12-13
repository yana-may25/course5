package database2;

import classes.Users;
//import model.Passengers;

import java.util.ArrayList;


public interface IUsers {

    void insert(Users obj);
    public ArrayList<Users> selectUsers();
    public void add(Users user);
    public void delete(Users user);
    public void updateName (String name, int id);

    void updateLogin(String name, int num);

    void updatePassword(String name, int num);

    void updatePassport(String name, int num);

    void updateSurname(String name, int num);

}
