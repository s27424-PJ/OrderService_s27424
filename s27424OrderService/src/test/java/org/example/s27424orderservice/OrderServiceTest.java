package org.example.s27424orderservice;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import java.util.List;

@SpringBootTest
public class OrderServiceTest {
    @Mock
    private OrderStorage orderStorage;
    @Mock
    private ProductStorage productStorage;
    @InjectMocks
    private OrderService orderService;
    @Test
    void order() {
        Product product= new Product("AC",2);
        Product products= new Product("AC",4);
        when(productStorage.getProductbyName(anyString())).thenReturn(products);
        assertThat(products.getIlosc()).isEqualTo(4);
        Order order=orderService.order(1, List.of(product),"Katowie");

        assertThat(order).isNotNull();
        verify(productStorage, times(1)).getProductbyName("AC");
        assertThat(products.getIlosc()).isEqualTo(2);
        verify(orderStorage, times(1)).addOrder(order);
    }
    @Test
    void order_throwexceptionbcsnoproduct() {
        Product product= new Product("AC",2);
        Product products= new Product("AC",1);
        when(productStorage.getProductbyName(anyString())).thenReturn(products);

        assertThat(products.getIlosc()).isEqualTo(1);
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(()->        orderService.order(1, List.of(product),"Katowie"));
        assertThat(products.getIlosc()).isEqualTo(1);
    }

    @Test
    void order_nullexception() {

        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(()->orderService.order(1, List.of(),"Katowie"));
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
    void checkOrdethrowruntimeece() {


        when(orderStorage.getOrders()).thenReturn(List.of());

        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(()->orderService.checkOrder(1));
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
    @Test
    void cancelOrderthrowrun_realizacja() {
        Product product= new Product("AC",1);
        Order order=new Order(1,1,List.of(product),"A",Type.Realizaja);
        when(orderStorage.getOrders()).thenReturn(List.of(order));

        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(()->orderService.cancelOrder(1));
    }
    @Test
    void cancelOrderthrowrun_dostarczone() {
        Product product= new Product("AC",1);
        Order order=new Order(1,1,List.of(product),"A",Type.Dostarczone);
        when(orderStorage.getOrders()).thenReturn(List.of(order));

        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(()->orderService.cancelOrder(1));
    }

    @Test
    void deliveredOrder() {
        Product product= new Product("AC",1);
        Order order=new Order(1,1,List.of(product),"A",Type.Nowe);
        when(orderStorage.getOrders()).thenReturn(List.of(order));
        String result= orderService.deliveredOrder(1);
        assertThat(result).isNotNull();
        assertEquals("Status: Dostarczone Lista produktów:[Product{name='AC', ilosc=1}]", result);
    }
    @Test
    void deliveredOrderthrowrntime_deli() {
        Product product= new Product("AC",1);
        Order order=new Order(1,1,List.of(product),"A",Type.Anulowane);
        when(orderStorage.getOrders()).thenReturn(List.of(order));

        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(()->orderService.deliveredOrder(1));
    }

@Test
    void producExists(){
//        Product product
}
//private boolean producExists(String name,int ilosc){
//    Product product = productStorage.getProductbyName(name);
//
//    if(product!=null){
//        product.setIlosc(product.getIlosc()-ilosc);
//        if(product.getIlosc()<0){
//            throw new RuntimeException("Nie ma produktu");
//        }
//        return true;
//    }
//    else{
//        return false;
//    }
//}

//    Product product= new Product("AC",1);
//    Order order=new Order(1,1,List.of(product),"A",Type.Nowe);
//    when(orderStorage.getOrders()).thenReturn(List.of(order));
//    String result= orderService.checkOrder(1);
//    assertThat(result).isNotNull();
//    assertEquals("Status: Nowe Lista produktów:[Product{name='AC', ilosc=1}]", result);

}
