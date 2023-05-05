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

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import javafx.geometry.Pos;
import javafx.geometry.Insets;
import com.cbr.models.*;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TransactionPage extends StackPane {
    private HBox container;
    private TransactionProductCardList transactionProductCardList;
    private TransactionInvoiceCardList transactionInvoiceCardList;
    private TemporaryInvoice temporaryInvoice;
    @Setter
    private Double grandTotal = 0.0;
    private TitleDropdown temporaryInvoiceDropdown;
    private TitleDropdown customerDropdown;
    @Getter
    private List<InventoryProduct> productList;
    private Label grandTotalNumber;

    private List<Member> membersVipsList;


    private List<TemporaryInvoice> temporaryInvoiceList;

    private double managementContainerWidth;

    private VBox discountTaxContainer;

    private Label discountNumber;
    public TransactionPage() {
        super();

        // GET DATASTORE
        updateDatastore();

        // INIT VARS
        temporaryInvoice = new TemporaryInvoice("");

        // Setup Container
        container = new HBox();
        container.setAlignment(Pos.TOP_CENTER);
        container.prefWidthProperty().bind(this.widthProperty()); // bind the width of the HBox to the width of the ScrollPane
        container.prefHeightProperty().bind(this.heightProperty()); // bind the height of the HBox to the height of the ScrollPane
        container.setStyle("-fx-background-color:" + Theme.getPrimaryDark());

        // Create transactionProductCardList : LEFT HALF
        transactionProductCardList = new TransactionProductCardList(this, productList);

        // Create Management Container : RIGHT HALF
        VBox managementContainer = new VBox();

        managementContainerWidth = 0.5 * Theme.getScreenWidth();
        double managementContainerHeight = Theme.getScreenHeight();

        managementContainer.setAlignment(Pos.TOP_CENTER);
        managementContainer.setMinSize(managementContainerWidth, managementContainerHeight);
        managementContainer.setPrefSize(managementContainerWidth, managementContainerHeight);
        managementContainer.setMaxSize(managementContainerWidth, managementContainerHeight);
        managementContainer.setPadding(new Insets(20,0,100,0));
        managementContainer.setSpacing(0.02 * managementContainerHeight);

        // DROPDOWN CONTAINER
        HBox dropdownContainer = new HBox();
        dropdownContainer.setMinSize(0.8 * managementContainerWidth, 0.1 * managementContainerHeight);
        dropdownContainer.setPrefSize(0.8 * managementContainerWidth, 0.1 * managementContainerHeight);
        dropdownContainer.setMaxSize(0.8 * managementContainerWidth, 0.1 * managementContainerHeight);

        List<String> temp = new ArrayList<>();
        temporaryInvoiceDropdown = new TitleDropdown(0.4 * managementContainerWidth,  "Temporary Invoice", temp);
        updateTemporaryInvoiceDropdown();
        temporaryInvoiceDropdown.getDropdown().valueProperty().addListener((observable, oldValue, newValue) -> {
            EventHandler<Event> mouseEventFilter = Event::consume;
            EventHandler<Event> keyEventFilter = Event::consume;

            if ("New Temporary Invoice".equals(newValue) || newValue == null) {
                // Enable the customerDropdown
                customerDropdown.removeEventFilter(MouseEvent.ANY, mouseEventFilter);
                customerDropdown.removeEventFilter(KeyEvent.ANY, keyEventFilter);

                customerDropdown.getDropdown().setValue(null);

                this.temporaryInvoice = new TemporaryInvoice("");
                updateTemporaryInvoice();
            } else {
                // Disable the customerDropdown and set its value to the customer_id of the temporaryInvoiceDropdown.getDropdown(
                customerDropdown.addEventFilter(MouseEvent.ANY, mouseEventFilter);
                customerDropdown.addEventFilter(KeyEvent.ANY, keyEventFilter);

                Optional<TemporaryInvoice> temporaryInvoice = temporaryInvoiceList.stream()
                        .filter(ti -> ti.getId().equals(newValue))
                        .findFirst();
                String customerId = temporaryInvoice.get().getCustomerId();

                customerDropdown.getDropdown().setValue(customerId);

                this.temporaryInvoice = temporaryInvoice.get();
                updateTemporaryInvoice();
            }
        });

        // Dropdown Spacer
        Region spacerDropdown = new Region();
        HBox.setHgrow(spacerDropdown, Priority.ALWAYS);

        updateCustomerDropdown();

        // Add all dropdownContainer children
        dropdownContainer.getChildren().addAll(temporaryInvoiceDropdown, spacerDropdown, customerDropdown);

        // INVOICE CARD LIST
        transactionInvoiceCardList = new TransactionInvoiceCardList(this, 0.8 * managementContainerWidth, productList);
        VBox.setVgrow(transactionInvoiceCardList, Priority.ALWAYS);

        // DISCOUNT TAX CONTAINER
        discountTaxContainer = new VBox();
        discountTaxContainer.setMinWidth(0.8 * managementContainerWidth);
        discountTaxContainer.setPrefWidth(0.8 * managementContainerWidth);
        discountTaxContainer.setMaxWidth(0.8 * managementContainerWidth);

        // discount container
        HBox discountContainer = new HBox();
        discountContainer.setMinWidth(0.8 * managementContainerWidth);
        discountContainer.setPrefWidth(0.8 * managementContainerWidth);
        discountContainer.setMaxWidth(0.8 * managementContainerWidth);
        discountContainer.setPadding(new Insets(5,15,5,15));

        Label discountLabel = new Label("Discount");
        discountLabel.setFont(Theme.getBodyMediumFont());
        discountLabel.setTextFill(Color.WHITE);

        Region spacerDiscountContainer = new Region();
        HBox.setHgrow(spacerDiscountContainer, Priority.ALWAYS);

        discountNumber = new Label("0");
//        renderGrandTotal();
        discountNumber.setFont(Theme.getBodyMediumFont());
        discountNumber.setTextFill(Color.WHITE);

        discountContainer.getChildren().addAll(discountLabel, spacerDiscountContainer, discountNumber);

        discountTaxContainer.getChildren().add(discountContainer);


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
        saveBill.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                saveBill();
            }
        });

        // Button : Make Bill
        Button makeBill = new DefaultButton(0.38 * managementContainerWidth, 0.05 * managementContainerHeight, "Make Bill");
        makeBill.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                makeBill();
            }
        });

        // Add all buttonContainer children
        Region spacerButtonContainer = new Region();
        HBox.setHgrow(spacerButtonContainer, Priority.ALWAYS);
        buttonContainer.getChildren().addAll(saveBill, spacerButtonContainer, makeBill);

        // Add all managementContainer children
        managementContainer.getChildren().addAll(dropdownContainer, transactionInvoiceCardList, discountTaxContainer, grandTotalContainer, buttonContainer);

        // Add all container components
        container.getChildren().addAll(transactionProductCardList, managementContainer);

        // Set StackPane properties
        this.setMinSize(Theme.getScreenWidth(), Theme.getScreenHeight());
        this.getChildren().add(container);
    }

    public void updateDatastore() {
        productList = App.getDataStore().getInventory().getDataList();
        membersVipsList = App.getDataStore().getMembersVips();
        temporaryInvoiceList = App.getDataStore().getTemporaryInvoices().getDataList();
    }

    public void updateTemporaryInvoiceDropdown() {
        List<String> temporaryInvoiceListString = new ArrayList<>();
        temporaryInvoiceListString.add("New Temporary Invoice"); // Default value

        for (TemporaryInvoice ti : temporaryInvoiceList){
            temporaryInvoiceListString.add(ti.getId());
            System.out.println(ti.getId());
        }

        temporaryInvoiceDropdown.getDropdown().getItems().clear();
        temporaryInvoiceDropdown.getDropdown().getItems().addAll(temporaryInvoiceListString);
    }

    public void updateCustomerDropdown() {
        List<String> customers = new ArrayList<>();
        customers.add("New Customer"); // Default value

        for (Member m : membersVipsList){
            customers.add(m.getId());
        }

        customerDropdown = new TitleDropdown(0.4 * managementContainerWidth,  "Customer", customers);
    }

    public void saveBill() {
        String customerId = customerDropdown.getDropdown().getValue();
        if (customerId == null || customerId.equals("New Customer")){
            Customer newCustomer = new Customer();
            customerId = newCustomer.getId();
            App.getDataStore().addClient(newCustomer);
        }
        temporaryInvoice.setCustomerId(customerId);

        App.getDataStore().addTemporaryInvoice(temporaryInvoice);

        System.out.println(customerId);

        // SAVE TO DB
        updateDatastore();
        resetInfo();
        updateTemporaryInvoiceDropdown();

//        temporaryInvoice = new TemporaryInvoice("");
    }
    public void makeBill() {
        String customerId = customerDropdown.getDropdown().getValue();
        if (customerId == null || customerId.equals("New Customer")){
            Customer newCustomer = new Customer();
            customerId = newCustomer.getId();
            App.getDataStore().addClient(newCustomer);
        }

        List<BoughtProduct> products = new ArrayList<>();

        // PARSE TEMP BILL TO FIXED BILL
        for (Map.Entry<String, Integer> entry : temporaryInvoice.getProductFrequencies().entrySet()) {
            String productId = entry.getKey();

            Optional<InventoryProduct> inventoryProduct = productList.stream()
                    .filter(ip -> ip.getId().equals(productId))
                    .findFirst();

            if (inventoryProduct.isPresent()) {
                InventoryProduct product = inventoryProduct.get();
                BoughtProduct boughtProduct = new BoughtProduct(product, entry.getValue());
                products.add(boughtProduct);
            }
        }

        // IF USER IS MEMBERS OR VIP, SHOW POP UP "WANT TO USE POINTS?"


//        FixedInvoice invoice = new FixedInvoice(products, customerId);
//        App.getDataStore().addInvoice(invoice);
//

        App.getDataStore().deleteTemporaryInvoices(this.temporaryInvoice);

        // SAVE TO DB
        updateDatastore();
        resetInfo();
        updateTemporaryInvoiceDropdown();

//        temporaryInvoice = new TemporaryInvoice("");
    }

    public void addProduct(Product product) {
//        // Check if product already in invoice
//        if (temporaryInvoice.getProductFrequencies().containsKey(product.getId())){
//            transactionInvoiceCardList.removeInvoiceCard(product);
//        }

        temporaryInvoice.addProduct(product.getId());
        updateGrandTotal(product.getSellPrice());
        transactionInvoiceCardList.addInvoiceCard(product);
    }

    public void removeProduct(Product product) {
        temporaryInvoice.removeProduct(product.getId());
        updateGrandTotal(-product.getSellPrice());

        // Check if invoice need to be deleted
        if (!temporaryInvoice.getProductFrequencies().containsKey(product.getId())){
            transactionInvoiceCardList.removeInvoiceCard(product);
        }
    }

    public void updateGrandTotal(Double changes){
        grandTotal += changes;
        renderGrandTotal();
    }

    public void renderGrandTotal() {
        grandTotalNumber.setText(String.format("%.2f", grandTotal));
    }

    public void updateTemporaryInvoice(){
//        temporaryInvoice = invoice;

        resetInfo();

        for (Map.Entry<String, Integer> entry : temporaryInvoice.getProductFrequencies().entrySet()) {
            String productId = entry.getKey();

            Optional<InventoryProduct> inventoryProduct = productList.stream()
                    .filter(ip -> ip.getId().equals(productId))
                    .findFirst();

            if (inventoryProduct.isPresent()) {
                InventoryProduct product = inventoryProduct.get();
                int frequency = entry.getValue();
                for (int i = 0; i < frequency; i++) {
                    addProduct(product);
                }
            } else {
                // It means the product has been removed or not for sale anymore
            }
        }
    }

    public void resetInfo() {
        // RESET THE GRAND TOTAL
        setGrandTotal(0.0);
        renderGrandTotal();

        // RESET THE INVOICE CARDS CONTAINER
        transactionInvoiceCardList.getInvoiceListContainer().getChildren().clear();
    }
}