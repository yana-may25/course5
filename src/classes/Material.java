package classes;


import java.io.Serializable;

public class Material  implements Serializable {
    private int id;
    private String name;
    private String type;
    private double value;

    public Material() {}


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Material( String name, String type, double value) {
        this.name = name;
        this.type = type;
        this.value = value;
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

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
