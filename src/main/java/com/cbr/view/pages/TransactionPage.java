package com.cbr.view.pages;

import com.cbr.App;
import com.cbr.view.components.buttons.DefaultButton;
import com.cbr.view.components.cards.TransactionInvoiceCard;
import com.cbr.view.components.cards.TransactionProductCard;
import com.cbr.view.components.cardslist.TransactionInvoiceCardList;
import com.cbr.view.components.cardslist.TransactionProductCardList;
import com.cbr.view.components.dropdown.Dropdown;
import com.cbr.view.components.dropdown.TitleDropdown;
import com.cbr.view.components.labels.PageTitle;
import com.cbr.view.components.spinner.NumberSpinner;
import com.cbr.view.theme.Theme;
import com.cbr.App;

import javafx.scene.layout.*;

import javafx.geometry.Pos;
import javafx.geometry.Insets;
import com.cbr.models.*;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import java.util.List;

public class TransactionPage extends StackPane {
    private HBox container;
    private TransactionProductCardList transactionProductCardList;
    private TemporaryInvoice temporaryInvoice;
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

        // Create Management Container : RIGHT HALF
        VBox managementContainer = new VBox();

        double managementContainerWidth = 0.5 * Theme.getScreenWidth();
        double managementContainerHeight = Theme.getScreenHeight();

        managementContainer.setAlignment(Pos.TOP_CENTER);
        managementContainer.setMinSize(managementContainerWidth, managementContainerHeight);
        managementContainer.setPrefSize(managementContainerWidth, managementContainerHeight);
        managementContainer.setMaxSize(managementContainerWidth, managementContainerHeight);
        managementContainer.setPadding(new Insets(20,0,100,0));
        managementContainer.setSpacing(0.03 * managementContainerHeight);

        // Spacer
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        // DROPDOWN CONTAINER
        HBox dropdownContainer = new HBox();
        dropdownContainer.setMinSize(0.8 * managementContainerWidth, 0.1 * managementContainerHeight);
        dropdownContainer.setPrefSize(0.8 * managementContainerWidth, 0.1 * managementContainerHeight);
        dropdownContainer.setMaxSize(0.8 * managementContainerWidth, 0.1 * managementContainerHeight);

        TitleDropdown transactionDropdown = new TitleDropdown(0.4 * managementContainerWidth,  "Transaction");
        TitleDropdown customerDropdown = new TitleDropdown(0.4 * managementContainerWidth,  "Customer");

        // Add all dropdownContainer children
        dropdownContainer.getChildren().addAll(transactionDropdown, spacer, customerDropdown);

        // INVOICE CARD LIST
        TransactionInvoiceCardList transactionInvoiceCardList = new TransactionInvoiceCardList(0.8 * managementContainerWidth);
        VBox.setVgrow(transactionInvoiceCardList, Priority.ALWAYS);

        // BUTTON CONTAINER
        HBox buttonContainer = new HBox();
        buttonContainer.setAlignment(Pos.CENTER);
        buttonContainer.setMinSize(0.8 * managementContainerWidth, 0.05 * managementContainerHeight);
        buttonContainer.setPrefSize(0.8 * managementContainerWidth, 0.05 * managementContainerHeight);
        buttonContainer.setMaxSize(0.8 * managementContainerWidth, 0.05 * managementContainerHeight);

        // Button : Save Bill
        Button saveBill = new DefaultButton(0.38 * managementContainerWidth, 0.05 * managementContainerHeight, "Save Bill");

        // Button : Make Bill
        Button makeBill = new DefaultButton(0.38 * managementContainerWidth, 0.05 * managementContainerHeight, "Make Bill");

        // Add all buttonContainer children
        buttonContainer.getChildren().addAll(saveBill, spacer, makeBill);

        // Add all managementContainer children
        managementContainer.getChildren().addAll(dropdownContainer, transactionInvoiceCardList, buttonContainer);

        // Add all container components
        container.getChildren().addAll(transactionProductCardList, managementContainer);

        // Set StackPane properties
        this.setMinSize(Theme.getScreenWidth(), Theme.getScreenHeight());
        this.getChildren().add(container);
    }
}
