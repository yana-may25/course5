package classes;

import java.io.Serializable;

public class ClientCard implements Serializable {
    private int id;
    private String name;
    private String surname;
    private String number;
    private int purchases;
    private int discount;

    public ClientCard(String name, String surname, String number, int purchases) {
        this.name = name;
        this.surname = surname;
        this.number = number;
        this.purchases = purchases;
//        this.discount = discount;
    }

    public ClientCard() {
    }

    public ClientCard(int id, String name, String surname, String number, int purchases, int discount) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.number = number;
        this.purchases = purchases;
        this.discount = discount;
    }

    public ClientCard( String name, String surname, String number) {

        this.name = name;
        this.surname = surname;
        this.number = number;
    }


    public int getPurchases() {
        return purchases;
    }

    public void setPurchases(int purchases) {
        this.purchases = purchases;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
}
