package com.cbr;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Currency {
    private String symbol;
    private String name;
    private Integer value;
    private Double exchangeRate; // relative to USD

    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        DefaultPrettyPrinter prettyPrinter = new DefaultPrettyPrinter();
        prettyPrinter.indentArraysWith(DefaultIndenter.SYSTEM_LINEFEED_INSTANCE);
        prettyPrinter.indentObjectsWith(DefaultIndenter.SYSTEM_LINEFEED_INSTANCE);
        mapper.setDefaultPrettyPrinter(prettyPrinter);
        ObjectWriter writer = mapper.writer(prettyPrinter);

        Currency IDR = new Currency("IDR", "Indonesian Rupiah", 1, 1.0);
        Currency USD = new Currency ("USD", "US Dollar", 1, 14000.0);
        Currency AUD = new Currency ("AUD", "Australian Dollar", 1, 10000.0);
        Currency KRW = new Currency ("KRW", "Korean Won", 1, 11.0);

        List<Currency> curs = Arrays.asList(IDR, USD, AUD, KRW);

        try{
            String jsonDataString = writer.writeValueAsString(curs);
            Files.write(Paths.get("D:/lessons/SEM 4/OOP/TUBES/backup/CBR-Business-Management-App/Plugin-System-1/src/main/resources", "currency.json"), jsonDataString.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
