package org.example.s27424orderservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class S27424OrderServiceApplication implements CommandLineRunner {
    private final OrderStorage orderStorage;
    private final ProductStorage productStorage;
    private final OrderService orderService;

    public S27424OrderServiceApplication(OrderStorage orderStorage, ProductStorage productStorage, OrderService orderService) {
        this.orderStorage = orderStorage;
        this.productStorage = productStorage;
        this.orderService = orderService;
    }

    public static void main(String[] args) {
        SpringApplication.run(S27424OrderServiceApplication.class, args);
    }

    public void exec() {
        Product produca = new Product("Arbuz", 100);
        Product productb = new Product("Wafel", 6);
        productStorage.setProductList(List.of(productb,produca));
        System.out.println(productStorage.getProductList());
        Client client = new Client(1, "Arek");
        Client client1 = new Client(2, "Adam");
        Product product = new Product("Arbuz", 3);
        Product product1 = new Product("Wafel", 3);
        orderService.order(1, List.of(product1), "Warszawa");
        orderService.order(2, List.of(product1, product), "Warszawa");
        System.out.println(orderService.checkOrder(1));
        System.out.println(orderService.cancelOrder(2));
        System.out.println(orderService.deliveredOrder(1));
        System.out.println(product.getIlosc());
        System.out.println(orderStorage.getOrders());
        System.out.println(productStorage.getProductList());
    }


    @Override
    public void run(String... args) throws Exception {
        exec();
    }
}
