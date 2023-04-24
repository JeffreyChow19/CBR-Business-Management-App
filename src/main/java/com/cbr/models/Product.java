package com.cbr.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
@Setter
public class Product implements Serializable {
    private Integer id;
    private String productName;
    private Double buyPrice;
    private Double sellPrice;
    private Integer imagePath;
    private Integer stock;
    private String category;
    private Boolean status;
}
