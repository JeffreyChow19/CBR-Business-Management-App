package com.cbr;

import com.cbr.plugin.Plugin;
import com.cbr.view.MainView;
import com.cbr.view.components.dropdown.Dropdown;
import com.cbr.view.theme.Theme;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CurrencyPlugin implements Plugin {
    public String getName(){
        return "com.cbr.CurrencyPlugin";
    }
    public CurrencyPlugin(){

    }
    public void load(){
        Label currencyLabel = new Label("Currency");
        currencyLabel.setFont(Theme.getBodyFont());
        currencyLabel.setTextFill(Color.WHITE);
        try {
            ObjectMapper mapper = new ObjectMapper();
            System.out.println((Paths.get(getClass().getResource("currency.json").toURI())));
//            String invoicesJson = new String(Files.readAllBytes(Paths.get(getClass().getResource("currency.json").toURI())));
//            System.out.println(invoicesJson);
//            List<Currency> currencies = mapper.readValue(invoicesJson, new TypeReference<List<Currency>>() {});
//            App.getDataStore().setAdditionalData(currencies);
//            Dropdown dropdown = new Dropdown();
//            dropdown.getItems().addAll(currencies.stream()
//                    .map(Currency::getName)
//                    .collect(Collectors.toList()));
//            MainView.getInstance().getSettingsPage().getFormContainer().getChildren().addAll(currencyLabel, dropdown);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws URISyntaxException {
        CurrencyPlugin plug = new CurrencyPlugin();
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(plug.getClass().getResource("/").getPath());

    }
}
