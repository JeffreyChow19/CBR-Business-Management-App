package com.cbr;

import com.cbr.datastore.DataStore;
import com.cbr.models.TemporaryInvoice;
import com.cbr.plugin.Plugin;
import com.cbr.utils.AppSettings;
import com.cbr.view.MainView;
import com.cbr.view.components.dropdown.Dropdown;
import com.cbr.view.theme.Theme;
import javafx.geometry.Pos;
import javafx.scene.control.Label;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CurrencyPlugin implements Plugin {
    @Getter
    private Boolean status;
    private Dropdown currencySelector;
    public String getName(){
        return "com.cbr.CurrencyPlugin";
    }

    public CurrencyPlugin(){
        this.status = false;
    }
    public void load(){
        if(!this.status) {
            // seed to db
            Currency IDR = new Currency("IDR", "Indonesian Rupiah (Default)", 1, 1.0);
            Currency USD = new Currency ("USD", "US Dollar", 1, 14000.0);
            Currency AUD = new Currency ("AUD", "Australian Dollar", 1, 10000.0);
            Currency KRW = new Currency ("KRW", "Korean Won", 1, 11.0);
            System.out.println(MainView.getInstance().getSettingsPage());
            List<Currency> curs = new ArrayList<>(Arrays.asList(IDR, USD, AUD, KRW));

            DataStore dataStore = new DataStore(AppSettings.getInstance().getDataStoreMode(), AppSettings.getInstance().getDataStorePath());
            if (dataStore.<Currency>getAdditionalData("currency", Currency.class).isEmpty()){
                dataStore.setAdditionalData(curs, "currency");
            }

            VBox newFormContainer = new VBox();
            newFormContainer.setSpacing(30);
            newFormContainer.setAlignment(Pos.TOP_LEFT);
            Label currencyLabel = new Label("Currency");
            currencyLabel.setFont(Theme.getHeading2Font());
            currencyLabel.setTextFill(Color.WHITE);
            String defaultCurrency = AppSettings.getInstance().getAdditionalSettings().getOrDefault("currency", "IDR");
            List<String> symbols = new ArrayList<>();

            currencySelector = new Dropdown(new ArrayList<>());
            for (Currency c:curs){
                currencySelector.getItems().add(c.getSymbol() + " - " + c.getName());
                if (c.getSymbol().equals(defaultCurrency)){
                    currencySelector.setValue(c.getSymbol() + " - " + c.getName());
                }
            }

            currencySelector.valueProperty().addListener((observable, oldValue, newValue) -> {
                MainView.getInstance().getSettingsPage().getAdditionalValues().put("currency", newValue.split("-")[0].trim());
            });

            currencySelector.setValue("IDR - Indonesian Rupiah (Default)");

            newFormContainer.getChildren().addAll(currencyLabel, currencySelector);

            Label grandTotalLabel = new Label(defaultCurrency);
            grandTotalLabel.setFont(Theme.getHeading2Font());
            grandTotalLabel.setTextFill(Color.WHITE);
            MainView.getInstance().getTransactionPage().getGrandTotalContainer().getChildren().add(2, grandTotalLabel);

            AppSettings.getInstance().getAdditionalSettings().put("currency", "IDR");
            MainView.getInstance().getSettingsPage().getFormContainer().getChildren().add(newFormContainer);
            MainView.getInstance().getSettingsPage().getOnSaves().add(new CurrencyUpdate());
            this.status = true;
            System.out.println("I AM CALLEEDDD");
        }
    }
//    public static void main(String[] args) {
//        DataStore dataStore = new DataStore("JSON", "D:/lessons/SEM 4/OOP/TUBES/CBR-Business-Management-App/App/assets/data/json");
//        List <Currency>  curs = dataStore.<Currency>getAdditionalData("currency", Currency.class);
//        System.out.println(curs);
//    }

}
