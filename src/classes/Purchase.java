package classes;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Purchase implements Serializable {
    private int id;
    private String idOrder;
    private ArrayList<Jewellery> jewelleryArrayList;
    private int idClient;
    private LocalDate date;

    public Purchase() {}

    public Purchase(int id, String number, ArrayList<Jewellery>
            jewelleryArrayList, int idClient) {
        this.id=id;
        this.date =java.time.LocalDate.now();
        this.idOrder = number;
        this.jewelleryArrayList = jewelleryArrayList;
        this.idClient = idClient;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(String idOrder) {
        this.idOrder = idOrder;
    }

    public ArrayList<Jewellery> getJewelleryArrayList() {
        return jewelleryArrayList;
    }

    public void setJewelleryArrayList(ArrayList<Jewellery> jewelleryArrayList) {
        this.jewelleryArrayList = jewelleryArrayList;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
