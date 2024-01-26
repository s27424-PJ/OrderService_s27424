package org.example.s27424orderservice;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private int id_order;
    private int id_klient;
    private List<Product> productList = new ArrayList<>();
    private String adres;
    private Type status;

    public Order(int id_order, int id_klient, List<Product> productList, String adres, Type status) {
        this.id_order = id_order;
        this.id_klient = id_klient;
        this.productList = productList;
        this.adres = adres;
        this.status = status;
    }

    public int getId_order() {
        return id_order;
    }

    public void setId_order(int id_order) {
        this.id_order = id_order;
    }

    public int getId_klient() {
        return id_klient;
    }

    public void setId_klient(int id_klient) {
        this.id_klient = id_klient;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public Type getStatus() {
        return status;
    }

    public void setStatus(Type status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id_order=" + id_order +
                ", id_klient=" + id_klient +
                ", productList=" + productList +
                ", adres='" + adres + '\'' +
                ", status=" + status +
                '}';
    }
}
