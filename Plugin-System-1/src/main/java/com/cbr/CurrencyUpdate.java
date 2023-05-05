package com.cbr;

import com.cbr.datastore.DataStore;
import com.cbr.models.DataList;
import com.cbr.models.InventoryProduct;
import com.cbr.models.Pricing.BasePrice;
import com.cbr.models.Pricing.Price;
import com.cbr.utils.AppSettings;
import com.cbr.utils.SettingsUpdate;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CurrencyUpdate implements SettingsUpdate {
    public void onSave(){
        List<Currency>  currencyList = App.getDataStore().<Currency>getAdditionalData("currency", Currency.class);
        String currencySymbol = AppSettings.getInstance().getAdditionalSettings().get("currency");
        Currency baseCurrency = currencyList.stream()
                .filter(obj -> obj.getSymbol().equals(currencySymbol))
                .findFirst().get(); // app currency

        Double exchange = baseCurrency.getExchangeRate();
        List<InventoryProduct> updatedCurrencyInventory = App.getDataStore().getInventory().getDataList();
        for (InventoryProduct p : App.getDataStore().getInventory().getDataList()){
            if (p.getAdditionalValues().get("currency")!=null){ // old currency in datastore
                Currency oldCurrencyTemp = currencyList.stream()
                        .filter(obj -> obj.getSymbol().equals(p.getAdditionalValues().get("currency")))
                        .findFirst().get();

                exchange = exchange / oldCurrencyTemp.getValue();
            }
            System.out.println(p);
            Price newPrice = new CurrencyPrice(new BasePrice(p.getSellPrice().getValue() / exchange));
            p.setSellPrice(newPrice);
            newPrice = new CurrencyPrice(new BasePrice(p.getBuyPrice().getValue() / exchange));
            p.setBuyPrice(newPrice);
            p.getAdditionalValues().put("currency", baseCurrency.getSymbol());
        }
        App.getDataStore().setInventory(App.getDataStore().getInventory());
    }
}
