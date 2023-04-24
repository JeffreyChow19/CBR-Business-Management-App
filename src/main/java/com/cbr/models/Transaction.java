package com.cbr.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Hashtable;

@AllArgsConstructor
@Getter
@Setter
public class Transaction implements Serializable {
    private Hashtable<Integer, Integer> productFrequencies;
    // <ProductId, Count>
}
