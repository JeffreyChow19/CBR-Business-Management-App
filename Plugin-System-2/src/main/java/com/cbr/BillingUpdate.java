package com.cbr;

import com.cbr.models.TemporaryInvoice;
import com.cbr.utils.AppSettings;
import com.cbr.utils.SettingsUpdate;
import com.cbr.view.MainView;

public class BillingUpdate implements SettingsUpdate {
    public void onSave(){
        String taxPercentage = AppSettings.getInstance().getAdditionalSettings().get("tax");
        if (taxPercentage!=null){
            TemporaryInvoice.addAdditionalCosts("Tax", Double.parseDouble(taxPercentage)/100);
        }
        String serviceChargePercentage = AppSettings.getInstance().getAdditionalSettings().get("service charge");
        if (serviceChargePercentage!=null){
            TemporaryInvoice.addAdditionalCosts("Service Charge", Double.parseDouble(serviceChargePercentage)/100);
        }
//        MainView.getInstance().getSettingsPage().
    }
}
