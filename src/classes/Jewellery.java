package classes;

import java.io.Serializable;

public class Jewellery implements Serializable {
    private int id;
    private String type;
    private String collection;
    private String material1;
    private double weight1;
    private String material2;
    private double weight2;
    private double price;

    public String getName() {
        return name;
    }

    private String name;
    public Jewellery(String type, String collection, String material1,
                     double weight1, String material2, double weight2, double price) {
        this.type = type;
        this.collection = collection;
        this.material1 = material1;
        this.weight1 = weight1;
        this.material2 = material2;
        this.weight2 = weight2;
        this.price = price;
    }

    public Jewellery() {}

    public Jewellery(int id, String name) {
        this.id = id;
        this.name = name;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getMaterial1() {
        return material1;
    }

    public void setMaterial1(String material1) {
        this.material1 = material1;
    }

    public double getWeight1() {
        return weight1;
    }

    public void setWeight1(double weight1) {
        this.weight1 = weight1;
    }

    public String getMaterial2() {
        return material2;
    }

    public void setMaterial2(String material2) {
        this.material2 = material2;
    }

    public double getWeight2() {
        return weight2;
    }

    public void setWeight2(double weight2) {
        this.weight2 = weight2;
    }

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    public void setName(String name) {

        this.name = name;
    }

    @Override
    public String toString() {
        return type +id + "_"+ collection.substring(0,2) + '_' + material1.substring(0,2) + '_' + material2.substring(0,2);
    }

}
