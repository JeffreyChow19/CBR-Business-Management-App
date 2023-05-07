package com.cbr.view.pages;

import com.cbr.view.components.cardslist.CustomerHistoryCardList;
import com.cbr.view.components.labels.PageTitle;
import com.cbr.view.theme.Theme;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class CustomerHistoryPage extends StackPane {
    public CustomerHistoryPage(String customerId) {
        this.setMinSize(Theme.getScreenWidth(), Theme.getScreenHeight());

        VBox container = new VBox();
        container.setAlignment(Pos.TOP_CENTER);
        container.setPadding(new Insets(40,0,100,0));
        container.prefWidthProperty().bind(this.widthProperty()); // bind the width of the HBox to the width of the StackPane
        container.prefHeightProperty().bind(this.heightProperty()); // bind the height of the HBox to the height of the StackPane
        container.setStyle("-fx-background-color:" + Theme.getPrimaryDark());
        container.setSpacing(30);

        PageTitle title = new PageTitle("History of Customer " + customerId);

        CustomerHistoryCardList customerHistoryCardList = new CustomerHistoryCardList(customerId);
        VBox.setVgrow(customerHistoryCardList, Priority.ALWAYS);

        container.getChildren().addAll(title, customerHistoryCardList);

        this.getChildren().add(container);
    }
}
