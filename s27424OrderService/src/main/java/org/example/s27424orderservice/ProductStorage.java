package org.example.s27424orderservice;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductStorage {
    List<Product> productList = new ArrayList<>();
    public void addProduct(Product product){
        productList.add(product);
    }
    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
public Product getProductbyName(String name){
        for(Product product : productList){
            if(name.equals(product.getName())){

                return  product;

            }

        }
        return null;

}

}
