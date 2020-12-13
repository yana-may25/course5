package classes;

import java.io.Serializable;

public class JCollection implements Serializable {
    private int id;
    private String name;
    private int year;
    private double index;

    public JCollection(String name, int year, double index) {
        this.name = name;
        this.year = year;
        this.index = index;
    }

    public JCollection(int id, String name, int year, double index) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.index = index;
    }

    public JCollection() {
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getIndex() {
        return index;
    }

    public void setIndex(double index) {
        this.index = index;
    }
}
