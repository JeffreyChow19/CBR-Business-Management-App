package com.cbr.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class DataList<T extends Identifiable> implements Serializable {
    private List<T> dataList;
    public void add(T data){
        this.dataList.add(data);
    }
    public T getById(String id){
        return dataList.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public DataList(){
        this.dataList = new ArrayList<T>();
    }

    public int generateId(){
        int maxId = Integer.MIN_VALUE;
        for (Identifiable t : this.dataList) {
            String idString = t.getId();
            System.out.println(idString);
            int id = Integer.parseInt(idString.substring(3));
            if (id > maxId) {
                maxId = id;
            }
        }

        maxId++;
        return maxId;
    }
}
