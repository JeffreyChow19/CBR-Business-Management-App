package com.cbr;

import com.cbr.datastore.DataStore;
import com.cbr.models.DataList;
import com.cbr.models.InventoryProduct;
import com.cbr.models.Pricing.BasePrice;
import com.cbr.models.Pricing.Price;
import com.cbr.utils.AppSettings;
import com.cbr.utils.SettingsUpdate;
import com.cbr.view.MainView;
import com.cbr.view.components.header.tabmenu.TabMenuBar;
import com.cbr.view.pages.TransactionPage;
import com.cbr.view.theme.Theme;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class CurrencyUpdate implements SettingsUpdate {
    public void onSave(){
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
        Label grandTotalLabel = new Label(currencySymbol);
        grandTotalLabel.setFont(Theme.getHeading2Font());
        grandTotalLabel.setTextFill(Color.WHITE);
//        MainView.getInstance().getTransactionPage().getGrandTotalContainer().getChildren().add(2, grandTotalLabel);
        List<TransactionPage> transactionPages = TabMenuBar.getInstance().getTabs()
                .stream()
                .filter(tab -> tab.getContent() instanceof TransactionPage)
                .map(tab -> (TransactionPage) tab.getContent())
                .collect(Collectors.toList());
        System.out.println("bangudahbang");
        transactionPages.stream().forEach(transactionPage -> {
            System.out.println(transactionPage.getGrandTotalContainer().getChildren().size());
            if (transactionPage.getGrandTotalContainer().getChildren().size() == 3){
                transactionPage.getGrandTotalContainer().getChildren().add(2, grandTotalLabel);
            }
        });

    }
}
