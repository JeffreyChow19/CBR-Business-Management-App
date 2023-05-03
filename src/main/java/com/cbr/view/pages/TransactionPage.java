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
import javafx.scene.paint.Color;
import lombok.Getter;

import java.util.List;

public class TransactionPage extends StackPane {
    private HBox container;
    private TransactionProductCardList transactionProductCardList;
    private TransactionInvoiceCardList transactionInvoiceCardList;
    private TemporaryInvoice temporaryInvoice;
    private Double grandTotal = 0.0;
    private TitleDropdown transactionDropdown;
    private TitleDropdown customerDropdown;
    @Getter
    private List<InventoryProduct> productList;
    private Label grandTotalNumber;
    public TransactionPage() {
        super();

        temporaryInvoice = new TemporaryInvoice("");

        // Setup Container
        container = new HBox();
        container.setAlignment(Pos.TOP_CENTER);
        container.prefWidthProperty().bind(this.widthProperty()); // bind the width of the HBox to the width of the ScrollPane
        container.prefHeightProperty().bind(this.heightProperty()); // bind the height of the HBox to the height of the ScrollPane
        container.setStyle("-fx-background-color:" + Theme.getPrimaryDark());

        // Create transactionProductCardList : LEFT HALF
        productList = App.getDataStore().getInventory().getDataList();
        transactionProductCardList = new TransactionProductCardList(this, productList);

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

        // DROPDOWN CONTAINER
        HBox dropdownContainer = new HBox();
        dropdownContainer.setMinSize(0.8 * managementContainerWidth, 0.1 * managementContainerHeight);
        dropdownContainer.setPrefSize(0.8 * managementContainerWidth, 0.1 * managementContainerHeight);
        dropdownContainer.setMaxSize(0.8 * managementContainerWidth, 0.1 * managementContainerHeight);

        transactionDropdown = new TitleDropdown(0.4 * managementContainerWidth,  "Transaction");

        // Dropdown Spacer
        Region spacerDropdown = new Region();
        HBox.setHgrow(spacerDropdown, Priority.ALWAYS);

        customerDropdown = new TitleDropdown(0.4 * managementContainerWidth,  "Customer");

        // Add all dropdownContainer children
        dropdownContainer.getChildren().addAll(transactionDropdown, spacerDropdown, customerDropdown);

        // INVOICE CARD LIST
        transactionInvoiceCardList = new TransactionInvoiceCardList(0.8 * managementContainerWidth);
        VBox.setVgrow(transactionInvoiceCardList, Priority.ALWAYS);

        // GRAND TOTAL
        HBox grandTotalContainer = new HBox();
        grandTotalContainer.setMinWidth(0.8 * managementContainerWidth);
        grandTotalContainer.setPrefWidth(0.8 * managementContainerWidth);
        grandTotalContainer.setMaxWidth(0.8 * managementContainerWidth);
        grandTotalContainer.setStyle("-fx-border-color: white");
        grandTotalContainer.setPadding(new Insets(15));

        Label grandTotalLabel = new Label("Grand Total");
        grandTotalLabel.setFont(Theme.getHeading2Font());
        grandTotalLabel.setTextFill(Color.WHITE);

        Region spacerGrandTotal = new Region();
        HBox.setHgrow(spacerGrandTotal, Priority.ALWAYS);

        grandTotalNumber = new Label();
        renderGrandTotal();
        grandTotalNumber.setFont(Theme.getHeading2Font());
        grandTotalNumber.setTextFill(Color.WHITE);

        grandTotalContainer.getChildren().addAll(grandTotalLabel, spacerGrandTotal, grandTotalNumber);

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
        Region spacerButtonContainer = new Region();
        HBox.setHgrow(spacerButtonContainer, Priority.ALWAYS);
        buttonContainer.getChildren().addAll(saveBill, spacerButtonContainer, makeBill);

        // Add all managementContainer children
        managementContainer.getChildren().addAll(dropdownContainer, transactionInvoiceCardList, grandTotalContainer, buttonContainer);

        // Add all container components
        container.getChildren().addAll(transactionProductCardList, managementContainer);

        // Set StackPane properties
        this.setMinSize(Theme.getScreenWidth(), Theme.getScreenHeight());
        this.getChildren().add(container);
    }

    public void loadCustomerDropdown() {}
    public void handleCustomerChange() {}

    public void loadTransactionDropdown() {}
    public void handleTransactionChange() {}

    public void saveBill() {}
    public void makeBill() {}

    public void addProduct(Product product) {
        temporaryInvoice.addProduct(product.getId());
        updateGrandTotal(product.getSellPrice());
        transactionInvoiceCardList.update();
    }

    public void removeProduct(Product product) {
        temporaryInvoice.removeProduct(product.getId());
        updateGrandTotal(-product.getSellPrice());
    }

    public void updateGrandTotal(Double changes){
        grandTotal += changes;
        renderGrandTotal();
    }

    public void renderGrandTotal() {
        grandTotalNumber.setText(String.format("%.2f", grandTotal));
    }


}
