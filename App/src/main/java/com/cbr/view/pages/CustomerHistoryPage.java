package com.cbr.view.pages;

import com.cbr.view.components.cardslist.CustomerHistoryCardList;
import com.cbr.view.components.labels.PageTitle;
import javafx.scene.control.Label;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class CustomerHistoryPage extends VBox{
    public CustomerHistoryPage(String customerId) {
        Label title = new PageTitle("History of Customer " + customerId);

        CustomerHistoryCardList customerHistoryCardList = new CustomerHistoryCardList(customerId);
        VBox.setVgrow(customerHistoryCardList, Priority.ALWAYS);

        this.getChildren().addAll(title);
    }
}
