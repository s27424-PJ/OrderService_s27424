package org.example.s27424orderservice;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class OrderService {
    private  final OrderStorage orderStorage;
    private final ProductStorage productStorage;

    public OrderService(OrderStorage orderStorage, ProductStorage productStorage) {
        this.orderStorage = orderStorage;
        this.productStorage = productStorage;
    }

    private int id_order=0;
public Order order(int id_klienta, List<Product> produkty,String adres){
    for(Product product :produkty) {
            if(!producExists(product.getName(),product.getIlosc())){
            return null;
            }

    }
    if(produkty.isEmpty()){
        throw new RuntimeException("Lista produktów jest pusta");
    }
    Order order = new Order(++id_order,id_klienta,produkty,adres,Type.Nowe);
    orderStorage.addOrder(order);
    return order;
}
private boolean producExists(String name,int ilosc){
    Product product = productStorage.getProductbyName(name);

    if(product!=null){
        product.setIlosc(product.getIlosc()-ilosc);
        if(product.getIlosc()<0){
            throw new RuntimeException("Nie ma produktu");
        }
        return true;
    }
    else{
        return false;
    }
}
public String checkOrder(int id_order){
    List<Order> list=orderStorage.getOrders();
    for(Order order:list){
    if(id_order==order.getId_order()){
        return "Status: "+order.getStatus() + " Lista produktów:" +order.getProductList();
    }
    }
    throw new RuntimeException("Nie ma takiego orderu");
}
public String cancelOrder(int id_order){
    List<Order> list=orderStorage.getOrders();
    for(Order order:list) {
        if(id_order==order.getId_order() && order.getStatus()!=Type.Dostarczone&& order.getStatus()!=Type.Realizaja) {
        order.setStatus(Type.Anulowane);
        return "Status: " + order.getStatus()+" Lista produktów:"+order.getProductList();
        }

    }
    throw new RuntimeException("Error");
}
    public String deliveredOrder(int id_order){
        List<Order> list=orderStorage.getOrders();
        for(Order order:list) {
            if(id_order==order.getId_order() && order.getStatus()!=Type.Anulowane) {
                order.setStatus(Type.Dostarczone);
                return "Status: " + order.getStatus()+" Lista produktów:"+order.getProductList();
            }

        }

        throw new RuntimeException("Error");
    }

}
