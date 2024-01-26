package org.example.s27424orderservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class IntegrationTest {
    @Autowired
    private OrderService orderService;

    @MockBean
    private OrderStorage orderStorage;
    @Test
    void order() {
        Product product= new Product("AC",1);
        Order order=orderService.order(1, List.of(product),"Katowie");
        assertThat(order).isNotNull();
    }
    @Test
    void checkOrder() {
        Product product= new Product("AC",1);
        Order order=orderService.order(1, List.of(product),"Katowie");
        when(orderStorage.getOrders()).thenReturn(List.of(order));
        String result= orderService.checkOrder(1);
        assertThat(result).isNotNull();
        assertEquals("Status: Nowe Lista produktów:[Product{name='AC', ilosc=1}]", result);
    }
    @Test
    void cancelOrder() {
        Product product= new Product("AC",1);
        Order order=new Order(1,1,List.of(product),"A",Type.Nowe);
        when(orderStorage.getOrders()).thenReturn(List.of(order));
        String result= orderService.cancelOrder(1);
        assertThat(result).isNotNull();
        assertEquals("Status: Anulowane Lista produktów:[Product{name='AC', ilosc=1}]", result);
    }

}
