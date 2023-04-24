package com.cbr.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class CustomerList {
    private List<Customer> dataList;
    public void add(Customer data){
        this.dataList.add(data);
    }
    public Customer getById(Integer id){
        return dataList.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public CustomerList(){
        this.dataList = new ArrayList<Customer>();
    }

}
