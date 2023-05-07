package com.cbr.view.components.buttons;

import com.cbr.view.components.header.tabmenu.TabMenuBar;
import com.cbr.view.pages.ProfileEditor;
import com.cbr.view.theme.Theme;
import com.cbr.App;
import com.cbr.datastore.DataStore;

public class UpgradeButton extends DefaultButton {
    private String customerId;
    public UpgradeButton(String customerId){
        super(Theme.getScreenWidth()*0.08, Theme.getScreenHeight()*0.05,"Upgrade");
        this.customerId = customerId;
        DataStore customerList = App.getDataStore();
        this.setOnMouseClicked(event -> TabMenuBar.getInstance().addTab("Upgrade Customer#" + this.customerId, new ProfileEditor(customerList.getCustomerById(customerId))));
    }
}
