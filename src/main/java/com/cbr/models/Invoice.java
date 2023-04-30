package com.cbr.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

@NoArgsConstructor
@Getter
@Setter
public abstract class Invoice extends Identifiable {
    protected String customerId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    protected LocalDateTime createdAt;

    public Invoice(String customerId){
        this.customerId = customerId;
        this.createdAt = LocalDateTime.now();
    }

    public void print(){
        // to implement
    }
}
