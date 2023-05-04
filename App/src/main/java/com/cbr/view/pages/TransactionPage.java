package com.cbr.view.pages;

import com.cbr.App;
import com.cbr.view.components.cardslist.TransactionProductCardList;
import com.cbr.view.components.dropdown.Dropdown;
import com.cbr.view.theme.Theme;

import javafx.scene.layout.VBox;

import javafx.geometry.Pos;
import com.cbr.models.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

import java.util.List;

public class TransactionPage extends StackPane {
    private HBox container;
    private TransactionProductCardList transactionProductCardList;
    public TransactionPage() {
        super();

        // Setup Container
        container = new HBox();
        container.setAlignment(Pos.TOP_CENTER);
        container.prefWidthProperty().bind(this.widthProperty()); // bind the width of the HBox to the width of the ScrollPane
        container.prefHeightProperty().bind(this.heightProperty()); // bind the height of the HBox to the height of the ScrollPane
        container.setStyle("-fx-background-color:" + Theme.getPrimaryDark());

        // Create transactionProductCardList : Left Part
        List<InventoryProduct> productList = App.getDataStore().getInventory().getDataList();
        transactionProductCardList = new TransactionProductCardList(productList);

        // Create Management Container : Right Part
        VBox managementContainer = new VBox();

        double managementContainerWidth = 0.5 * Theme.getScreenWidth();
        double managementContainerHeight = Theme.getScreenHeight();

        managementContainer.setMinSize(managementContainerWidth, managementContainerHeight);
        managementContainer.setPrefSize(managementContainerWidth, managementContainerHeight);
        managementContainer.setMaxSize(managementContainerWidth, managementContainerHeight);
        managementContainer.setStyle("-fx-background-color:" + Theme.getPrimaryBase() + ";");

        // Create dropdown
        Dropdown dropdown = new Dropdown();

        managementContainer.getChildren().addAll(dropdown);

        // Add all container components
        container.getChildren().addAll(transactionProductCardList, managementContainer);

        // Set StackPane properties
        this.setMinSize(Theme.getScreenWidth(), Theme.getScreenHeight());
        this.getChildren().add(container);
    }
}