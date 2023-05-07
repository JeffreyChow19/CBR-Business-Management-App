package com.cbr;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Currency implements Serializable {
    private String symbol;
    private String name;
    private Double exchangeRate; // relative to USD
}
