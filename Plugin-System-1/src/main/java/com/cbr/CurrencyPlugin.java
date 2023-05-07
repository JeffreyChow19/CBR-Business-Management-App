package com.cbr;

import com.cbr.models.BoughtProduct;
import com.cbr.models.FixedInvoice;
import com.cbr.models.InventoryProduct;
import com.cbr.models.Member;
import com.cbr.models.Pricing.BasePrice;
import com.cbr.models.Pricing.Price;
import com.cbr.plugin.Plugin;
import com.cbr.utils.AppSettings;
import com.cbr.view.MainView;
import com.cbr.view.components.form.dropdown.Dropdown;
import com.cbr.view.components.header.tabmenu.TabMenuBar;
import com.cbr.view.pages.TransactionPage;
import com.cbr.view.theme.Theme;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Label;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CurrencyPlugin implements Plugin {
    @NotNull
    private Dropdown currencySelector;

    public String getName() {
        return "com.cbr.CurrencyPlugin";
    }

    public void load() {
        Currency IDR = new Currency("IDR", "Indonesian Rupiah (Default)", 1.0);
        Currency USD = new Currency("USD", "US Dollar", 14000.0);
        Currency AUD = new Currency("AUD", "Australian Dollar", 10000.0);
        Currency KRW = new Currency("KRW", "Korean Won", 11.0);
        List<Currency> curs = new ArrayList<>(Arrays.asList(IDR, USD, AUD, KRW));

        if (App.getDataStore().<Currency>getAdditionalData("currency", Currency.class).isEmpty()) {
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
        for (Currency c : curs) {
            currencySelector.getItems().add(c.getSymbol() + " - " + c.getName());
            if (c.getSymbol().equals(defaultCurrency)) {
                currencySelector.setValue(c.getSymbol() + " - " + c.getName());
            }
        }

        currencySelector.valueProperty().addListener((observable, oldValue, newValue) -> {
            MainView.getInstance().getSettingsPage().getAdditionalValues().put("currency",
                    newValue.split("-")[0].trim());
        });

        newFormContainer.getChildren().addAll(currencyLabel, currencySelector);

        MainView.getInstance().getSettingsPage().getFormContainer().getChildren().add(newFormContainer);

        Thread task = new Thread(() -> {
            while (true) {
                if (App.getDataStore().<Currency>getAdditionalData("currency", Currency.class).isEmpty()) {
                    App.getDataStore().setAdditionalData(curs, "currency");
                }
                List<Currency> currencyList = App.getDataStore().<Currency>getAdditionalData("currency",
                        Currency.class);
                String currencySymbol = AppSettings.getInstance().getAdditionalSettings().get("currency");
                Currency baseCurrency = currencyList.stream()
                        .filter(obj -> obj.getSymbol().equals(currencySymbol))
                        .findFirst().get(); // get app currency

                for (InventoryProduct p : App.getDataStore().getInventory().getDataList()) {
                    Double exchange = baseCurrency.getExchangeRate();
                    if (p.getAdditionalValues().get("currency") != null) { // old currency in datastore
                        Currency oldCurrencyTemp = currencyList.stream()
                                .filter(obj -> obj.getSymbol().equals(p.getAdditionalValues().get("currency")))
                                .findFirst().get();
                        exchange = exchange / oldCurrencyTemp.getExchangeRate();
                    }
                    Price newPrice = new CurrencyPrice(new BasePrice(p.getSellPrice().getValue() / exchange),
                            baseCurrency.getSymbol());
                    p.setSellPrice(newPrice);
                    newPrice = new CurrencyPrice(new BasePrice(p.getBuyPrice().getValue() / exchange),
                            baseCurrency.getSymbol());
                    p.setBuyPrice(newPrice);
                    p.getAdditionalValues().put("currency", baseCurrency.getSymbol());
                }
                App.getDataStore().setInventory(App.getDataStore().getInventory());

                for (FixedInvoice invoice : App.getDataStore().getInvoices().getDataList()) {
                    String temp = invoice.getBoughtProducts().get(0).getAdditionalValues().get("currency");
                    if (temp != null) {
                        for (BoughtProduct product : invoice.getBoughtProducts()) {
                            if (!(product.getSellPrice() instanceof CurrencyPrice)) {
                                product.setBuyPrice(new CurrencyPrice(product.getBuyPrice(), temp));
                                product.setSellPrice(new CurrencyPrice(product.getSellPrice(), temp));
                            } else {
                                break;
                            }
                        }
                        if (!(invoice.getGrandTotal() instanceof CurrencyPrice)) {
                            invoice.setGrandTotal(new CurrencyPrice(invoice.getGrandTotal(), temp));
                            invoice.setGetPoint(new CurrencyPrice(invoice.getGetPoint(), temp));
                        }
                    }
                }

                List<TransactionPage> transactionPages = TabMenuBar.getInstance().getTabs()
                        .stream()
                        .filter(tab -> tab.getContent() instanceof TransactionPage)
                        .map(tab -> (TransactionPage) tab.getContent())
                        .collect(Collectors.toList());

                for (Member member : App.getDataStore().getMembersVips()) {
                    Double exchange = baseCurrency.getExchangeRate();
                    if (member.getAdditionalValue().get("currency") != null) { // old currency in datastore
                        Currency oldCurrencyTemp = currencyList.stream()
                                .filter(obj -> obj.getSymbol().equals(member.getAdditionalValue().get("currency")))
                                .findFirst().get();
                        exchange = exchange / oldCurrencyTemp.getExchangeRate();
                    }
                    Price newPoint = new CurrencyPrice((new BasePrice(member.getPoint().getValue() / exchange)),
                            baseCurrency.getSymbol());
                    member.setPoint(newPoint);
                    member.getAdditionalValue().put("currency", baseCurrency.getSymbol());
                }

                Platform.runLater(() -> {
                    transactionPages.stream().forEach(transactionPage -> {
                        Label grandTotalLabel = new Label(currencySymbol);
                        grandTotalLabel.setFont(Theme.getHeading2Font());
                        grandTotalLabel.setTextFill(Color.WHITE);
                        // system.out.println(transactionPage.getGrandTotalContainer().getChildren().size());
                        if (transactionPage.getGrandTotalContainer().getChildren().size() == 3) {
                            transactionPage.getGrandTotalContainer().getChildren().add(2, grandTotalLabel);
                        } else {
                            transactionPage.getGrandTotalContainer().getChildren().set(2, grandTotalLabel);
                        }
                    });
                });
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        task.start();
    }
}
