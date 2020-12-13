package classes;

import java.io.Serializable;
import java.util.Objects;

public class Users implements Serializable {
    private int idUser;
    private String login;
    private String password;
    private String surname;
    private String name;
    private String passportNumber;
    private boolean isAdmin;


    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
    public boolean isAdmin() {
        return isAdmin;
    }



    public Users( int idUser,
                  String login1, String password, String surname, String name, String passportNumber) {

        this.idUser = idUser;
        this.login = login1;
        this.password = password;
        this.surname = surname;
        this.name = name;
        this.passportNumber = passportNumber;

    }

    public Users(String login1, String password, String surname, String name, String passportNumber, boolean isAdmin) {
        this.login = login1;
        this.password = password;
        this.surname = surname;
        this.name = name;
        this.passportNumber = passportNumber;
        this.isAdmin = isAdmin;
    }

    public Users() { }


    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Users that = (Users) o;

        return Objects.equals(this.idUser, that.idUser) &&
                Objects.equals(this.login, that.login) &&
                Objects.equals(this.password, that.password) &&
                Objects.equals(this.name, that.name) &&
                Objects.equals(this.passportNumber, that.passportNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.idUser, this.login, this.password,
                this.name,this.passportNumber);
    }

    @Override
    public String toString() {
        return "Users{" +
                "idUser=" + idUser +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", passportNumber=" + passportNumber +
                '}';
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

}
