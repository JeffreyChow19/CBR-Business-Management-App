package com.cbr.models.Pricing;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(as=BasePrice.class)
@JsonDeserialize(as=BasePrice.class)
public interface Price {
    public Double getValue();
    public String toString();
}
