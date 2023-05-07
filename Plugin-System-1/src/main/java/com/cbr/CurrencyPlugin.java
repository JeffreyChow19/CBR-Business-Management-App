package com.cbr;

import com.cbr.models.InventoryProduct;
import com.cbr.models.Pricing.BasePrice;
import com.cbr.models.Pricing.Price;
import com.cbr.plugin.Plugin;
import com.cbr.utils.AppSettings;
import com.cbr.view.MainView;
import com.cbr.view.components.dropdown.Dropdown;
import com.cbr.view.components.header.tabmenu.TabMenuBar;
import com.cbr.view.pages.TransactionPage;
import com.cbr.view.theme.Theme;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Label;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CurrencyPlugin implements Plugin {
    private Dropdown currencySelector;
    public String getName(){
        return "com.cbr.CurrencyPlugin";
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
            // seed to db

        VBox newFormContainer = new VBox();
        newFormContainer.setSpacing(30);
        newFormContainer.setAlignment(Pos.TOP_LEFT);
        Label currencyLabel = new Label("Currency");
        currencyLabel.setFont(Theme.getHeading2Font());
        currencyLabel.setTextFill(Color.WHITE);
        String defaultCurrency = AppSettings.getInstance().getAdditionalSettings().getOrDefault("currency", "IDR");

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

        Thread task = new Thread(() -> {
            while (true) {
                List<Currency>  currencyList = App.getDataStore().<Currency>getAdditionalData("currency", Currency.class);
                String currencySymbol = AppSettings.getInstance().getAdditionalSettings().get("currency");
                Currency baseCurrency = currencyList.stream()
                        .filter(obj -> obj.getSymbol().equals(currencySymbol))
                        .findFirst().get(); // get app currency

                for (InventoryProduct p : App.getDataStore().getInventory().getDataList()){
                    Double exchange = baseCurrency.getExchangeRate();
                    if (p.getAdditionalValues().get("currency")!=null){ // old currency in datastore
                        Currency oldCurrencyTemp = currencyList.stream()
                                .filter(obj -> obj.getSymbol().equals(p.getAdditionalValues().get("currency")))
                                .findFirst().get();
                        exchange = exchange / oldCurrencyTemp.getExchangeRate();
                    }
                    Price newPrice = new CurrencyPrice(new BasePrice(p.getSellPrice().getValue() / exchange));
                    p.setSellPrice(newPrice);
                    newPrice = new CurrencyPrice(new BasePrice(p.getBuyPrice().getValue() / exchange));
                    p.setBuyPrice(newPrice);
                    p.getAdditionalValues().put("currency", baseCurrency.getSymbol());
                }
                App.getDataStore().setInventory(App.getDataStore().getInventory());

                List<TransactionPage> transactionPages = TabMenuBar.getInstance().getTabs()
                        .stream()
                        .filter(tab -> tab.getContent() instanceof TransactionPage)
                        .map(tab -> (TransactionPage) tab.getContent())
                        .collect(Collectors.toList());

                Platform.runLater(() -> {
                    transactionPages.stream().forEach(transactionPage -> {
                        Label grandTotalLabel = new Label(currencySymbol);
                        grandTotalLabel.setFont(Theme.getHeading2Font());
                        grandTotalLabel.setTextFill(Color.WHITE);
                        System.out.println(transactionPage.getGrandTotalContainer().getChildren().size());
                        if (transactionPage.getGrandTotalContainer().getChildren().size() == 3){
                            transactionPage.getGrandTotalContainer().getChildren().add(2, grandTotalLabel);
                        }
                        else{
                            transactionPage.getGrandTotalContainer().getChildren().set(2, grandTotalLabel);
                        }
                    });
                });
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        task.start();
    }
}
