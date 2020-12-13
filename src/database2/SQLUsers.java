package database2;

import classes.Users;


import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;


public class SQLUsers implements IUsers {
    private ConnectionDB dbConnection;
    private static SQLUsers instance;

    public SQLUsers() {
        dbConnection = ConnectionDB.getInstance();
    }

    public static synchronized SQLUsers getInstance() {
        if (instance == null) {
            instance = new SQLUsers();
        }
        return instance;
    }

    @Override
    public void insert(Users obj) {
        String str = "INSERT INTO user (iduser, login, password, name, surname," +
                " passportNumber) VALUES('" + obj.getIdUser() + "'," +
                " '" + obj.getLogin() + "', '" + obj.getPassword() + "', " +
                "'" + obj.getSurname() + "', '" + obj.getName() + "', '" +
                "', " + obj.getPassportNumber() + ")";
        dbConnection.execute(str);

    }

    @Override
    public ArrayList<Users> selectUsers() {
//
        String str = "SELECT * From user";
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        ArrayList<Users> User = new ArrayList<>();

        for (String[] items : result) {
            Users user = new Users();
            user.setIdUser(Integer.parseInt(items[0]));
            user.setLogin(items[1]);
            user.setPassportNumber(items[2]);
            user.setName(items[3]);
            user.setSurname(items[4]);
            user.setPassportNumber(items[5]);
            user.setAdmin(Boolean.parseBoolean(items[6]));
            User.add(user);

        }
        return User;
    }

    @Override
    public void add(Users user) {
        try {
            String queryAdd = "INSERT into user (name, surname, login, " +
                    "pass, passport, isAdmin) VALUES (?,?,?,?,?,?)";
            PreparedStatement st = dbConnection.getConnect().prepareStatement(queryAdd);
            st.setString(1, user.getName());
            st.setString(2, user.getSurname());
            st.setString(3, user.getLogin());
            st.setString(4, user.getPassword());
            st.setString(5, user.getPassportNumber());
            st.setBoolean(6, user.isAdmin());
            st.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void delete(Users user) {
        try {
            String REMOVE_GROUP = "DELETE FROM user " + "WHERE iduser = ?";
            PreparedStatement statement = dbConnection.getConnect().prepareStatement(REMOVE_GROUP);
            statement.setInt(1, user.getIdUser());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean checkAdmin(Users user) {
        try {
            String REMOVE_GROUP = "SELECT isAdmin FROM user " + "WHERE iduser = ?";
            PreparedStatement statement = dbConnection.getConnect().prepareStatement(REMOVE_GROUP);
            statement.setInt(1, user.getIdUser());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user.isAdmin();
    }

    public void updateName(String name, int id) {
        try {
            String UPDATE_NAME = "UPDATE user " + "SET name = ?" + "WHERE iduser = ?";
            PreparedStatement statement = dbConnection.getConnect().prepareStatement(UPDATE_NAME);
            statement.setString(1, name);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateLogin(String name, int id) {
        try {
            String UPDATE_NAME = "UPDATE user " + "SET login = ?" + "WHERE iduser = ?";
            PreparedStatement statement = dbConnection.getConnect().prepareStatement(UPDATE_NAME);
            statement.setString(1, name);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updatePassword(String name, int id) {
        try {
            String UPDATE_NAME = "UPDATE user " + "SET password = ?" + "WHERE iduser = ?";
            PreparedStatement statement = dbConnection.getConnect().prepareStatement(UPDATE_NAME);
            statement.setString(1, name);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updatePassport(String name, int id) {
        try {
            String UPDATE_NAME = "UPDATE user " + "SET passport = ?" + "WHERE iduser = ?";
            PreparedStatement statement = dbConnection.getConnect().prepareStatement(UPDATE_NAME);
            statement.setString(1, name);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void updateSurname(String name, int id) {
        try {
            String UPDATE_NAME = "UPDATE user " + "SET surname = ?" + "WHERE iduser = ?";
            PreparedStatement statement = dbConnection.getConnect().prepareStatement(UPDATE_NAME);
            statement.setString(1, name);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
