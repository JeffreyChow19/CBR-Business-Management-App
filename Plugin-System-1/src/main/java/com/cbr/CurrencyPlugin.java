package com.cbr;

import com.cbr.datastore.DataStore;
import com.cbr.models.TemporaryInvoice;
import com.cbr.plugin.Plugin;
import com.cbr.utils.AppSettings;
import com.cbr.utils.SettingsUpdate;
import com.cbr.view.MainView;
import com.cbr.view.components.dropdown.Dropdown;
import com.cbr.view.theme.Theme;
import javafx.geometry.Pos;
import javafx.scene.control.Label;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CurrencyPlugin implements Plugin {
    @Getter @Setter
    private Boolean status;
    private Dropdown currencySelector;
    public String getName(){
        return "com.cbr.CurrencyPlugin";
    }

    public CurrencyPlugin(){
        this.status = false;
    }
    public void load(){
        Currency IDR = new Currency("IDR", "Indonesian Rupiah (Default)", 1.0);
        Currency USD = new Currency ("USD", "US Dollar", 14000.0);
        Currency AUD = new Currency ("AUD", "Australian Dollar",  10000.0);
        Currency KRW = new Currency ("KRW", "Korean Won", 11.0);
        List<Currency> curs = new ArrayList<>(Arrays.asList(IDR, USD, AUD, KRW));

        if (App.getDataStore().<Currency>getAdditionalData("currency", Currency.class).isEmpty()){
            App.getDataStore().setAdditionalData(curs, "currency");
        }
        if(!this.status) {
            // seed to db

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


            newFormContainer.getChildren().addAll(currencyLabel, currencySelector);

            MainView.getInstance().getSettingsPage().getFormContainer().getChildren().add(newFormContainer);
            MainView.getInstance().getSettingsPage().getOnSaves().add(new CurrencyUpdate());
            this.status = true;
        }
        SettingsUpdate update = new CurrencyUpdate();
        update.onSave();
    }
}
