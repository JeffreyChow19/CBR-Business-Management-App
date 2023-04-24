package com.cbr.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class Inventory implements Serializable {
    List<Product> productList;
    public void add(Product data){
        this.productList.add(data);
    }
    public Product getById(Integer id){
        return productList.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public Inventory(){
        this.productList = new ArrayList<Product>();
    }
}
