package org.example.s27424orderservice;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderStorage {
    List<Order> orders = new ArrayList<>();
    public void addOrder(Order order){
        orders.add(order);
    }

    public List<Order> getOrders() {
        return orders;
    }

    @Override
    public String toString() {
        return "OrderStorage{" +
                "orders=" + orders +
                '}';
    }
}
