package org.example.s27424orderservice;

public class Product {
    private String name;
    private int ilosc;

    public Product(String name, int ilosc) {
        this.name = name;
        this.ilosc = ilosc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIlosc() {
        return ilosc;
    }

    public void setIlosc(int ilosc) {
        this.ilosc = ilosc;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", ilosc=" + ilosc +
                '}';
    }
}
