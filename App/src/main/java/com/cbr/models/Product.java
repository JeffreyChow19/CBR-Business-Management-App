package com.cbr.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public abstract class Product extends Identifiable implements Serializable {
    private String productName;
    private String category;
    private Double sellPrice;
    private Double buyPrice;

//    public abstract Double getGrandTotal();
}
