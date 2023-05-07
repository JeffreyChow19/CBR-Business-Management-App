package com.cbr.view.components.buttons;

import com.cbr.view.components.header.tabmenu.TabMenuBar;
import com.cbr.view.pages.HistoryInvoicePage;
import com.cbr.view.theme.Theme;
import lombok.Getter;

@Getter
public class HistoryButton extends DefaultButton{
    private String customerId;
    public HistoryButton(String customerId){
        super(Theme.getScreenWidth()*0.08, Theme.getScreenHeight()*0.05,"History");
        this.customerId = customerId;
        this.setOnMouseClicked(event -> TabMenuBar.getInstance().addTab("History of Customer#" + this.customerId, new HistoryInvoicePage(customerId)));
    }
}
