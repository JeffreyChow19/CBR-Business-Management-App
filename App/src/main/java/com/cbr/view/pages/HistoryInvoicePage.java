package com.cbr.view.pages;

import com.cbr.App;
import com.cbr.models.FixedInvoice;
import com.cbr.view.components.buttons.DefaultButton;
import com.cbr.view.components.cardslist.HistoryInvoiceCardList;
import com.cbr.view.theme.Theme;
import com.cbr.view.components.labels.PageTitle;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.List;
import java.util.stream.Collectors;

public class HistoryInvoicePage extends VBox {
    public HistoryInvoicePage(String customerId) {
        PageTitle title = new PageTitle("History of Customer " + customerId);

        List<FixedInvoice> invoiceList = getHistoryInvoiceList(customerId);
        this.setMinSize(Theme.getScreenWidth(), Theme.getScreenHeight());
        HistoryInvoiceCardList historyInvoiceCardList = new HistoryInvoiceCardList(invoiceList);
        this.getChildren().addAll(title, historyInvoiceCardList);
        this.setAlignment(Pos.TOP_CENTER);
        this.setStyle("-fx-background-color:" + Theme.getPrimaryDark());
        this.setPadding(new Insets(30, 0, 120, 0));
        this.setSpacing(30);
        VBox.setVgrow(historyInvoiceCardList, Priority.ALWAYS);
    }

    public List<FixedInvoice> getHistoryInvoiceList(String customerId) {
        return App.getDataStore().getInvoices().getDataList().stream().filter(i -> i.getCustomerId().equals(customerId))
                .collect(Collectors.toList());
    }
}
