package com.cbr.view.pages;

import com.cbr.App;
import com.cbr.view.components.buttons.DefaultButton;
import com.cbr.view.components.cards.AdditionalCostCard;
import com.cbr.view.components.cards.TransactionInvoiceCard;
import com.cbr.view.components.cards.TransactionProductCard;
import com.cbr.view.components.cardslist.TransactionInvoiceCardList;
import com.cbr.view.components.cardslist.TransactionProductCardList;
import com.cbr.view.components.dropdown.Dropdown;
import com.cbr.view.components.dropdown.TitleDropdown;
import com.cbr.view.components.labels.PageTitle;
import com.cbr.view.components.popup.OkPopUp;
import com.cbr.view.components.popup.YesNoPopUp;
import com.cbr.view.components.spinner.NumberSpinner;
import com.cbr.view.theme.Theme;
import com.cbr.App;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import javafx.geometry.Pos;
import javafx.geometry.Insets;
import com.cbr.models.*;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TransactionPage extends StackPane {
    private HBox container;
    @Getter
    private HBox grandTotalContainer;
    private TransactionProductCardList transactionProductCardList;
    private TransactionInvoiceCardList transactionInvoiceCardList;
    @Getter
    private TemporaryInvoice temporaryInvoice;
    @Setter
    private Double grandTotal = 0.0;
    private TitleDropdown temporaryInvoiceDropdown;
    private TitleDropdown customerDropdown;
    @Getter
    private List<InventoryProduct> productList;
    private Label grandTotalNumber;
    private List<Member> membersVipsList;
    private Double discount;
    private List<TemporaryInvoice> temporaryInvoiceList;
    private double managementContainerWidth;
    @Getter
    private VBox additionalCostsContainer;
    private AdditionalCostCard discountContainer;

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

        // DEFINE managementContainer size
        managementContainerWidth = 0.5 * Theme.getScreenWidth();
        double managementContainerHeight = Theme.getScreenHeight();

        managementContainer.setAlignment(Pos.TOP_CENTER);
        managementContainer.setMinSize(managementContainerWidth, managementContainerHeight);
        managementContainer.setPrefSize(managementContainerWidth, managementContainerHeight);
        managementContainer.setMaxSize(managementContainerWidth, managementContainerHeight);
        managementContainer.setMaxSize(managementContainerWidth, managementContainerHeight);
        managementContainer.setPadding(new Insets(20,0,100,0));
        managementContainer.setSpacing(0.02 * managementContainerHeight);

        // DROPDOWNS CONTAINER
        HBox dropdownContainer = new HBox();
        dropdownContainer.setMinSize(0.8 * managementContainerWidth, 0.1 * managementContainerHeight);
        dropdownContainer.setPrefSize(0.8 * managementContainerWidth, 0.1 * managementContainerHeight);
        dropdownContainer.setMaxSize(0.8 * managementContainerWidth, 0.1 * managementContainerHeight);

        // Temporary Invoice Dropdown
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

                temporaryInvoice = App.getDataStore().getTemporaryInvoices().getById(newValue);
                String customerId = temporaryInvoice.getCustomerId();

                customerDropdown.getDropdown().setValue(customerId);

                updateTemporaryInvoice();
            }
        });

        // Dropdown Spacer
        Region spacerDropdown = new Region();
        HBox.setHgrow(spacerDropdown, Priority.ALWAYS);

        // Customer Dropdown
        updateCustomerDropdown();
        customerDropdown.getDropdown().valueProperty().addListener((observable, oldValue, newValue) -> {
            updateNumbers();
        });

        // Add all dropdownContainer children
        dropdownContainer.getChildren().addAll(temporaryInvoiceDropdown, spacerDropdown, customerDropdown);

        // INVOICE CARD LIST
        transactionInvoiceCardList = new TransactionInvoiceCardList(this, 0.8 * managementContainerWidth, productList);
        VBox.setVgrow(transactionInvoiceCardList, Priority.ALWAYS);

        // DISCOUNT TAX CONTAINER
        additionalCostsContainer = new VBox();
        additionalCostsContainer.setMinWidth(0.8 * managementContainerWidth);
        additionalCostsContainer.setPrefWidth(0.8 * managementContainerWidth);
        additionalCostsContainer.setMaxWidth(0.8 * managementContainerWidth);

        // Discount container
        discountContainer = new AdditionalCostCard(0.8 * managementContainerWidth, "Discount");
        updateDiscount();
        additionalCostsContainer.getChildren().addAll(discountContainer);

        // Add all additional costs components
        renderAdditionalCostsContainer();

        // GRAND TOTAL
        grandTotalContainer = new HBox();
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
        updateGrandTotal();
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
        managementContainer.getChildren().addAll(dropdownContainer, transactionInvoiceCardList, additionalCostsContainer, grandTotalContainer, buttonContainer);

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
            newCustomer.generateCustomerId();
            customerId = newCustomer.getId();
            App.getDataStore().addClient(newCustomer);
        }
        temporaryInvoice.setCustomerId(customerId);
        temporaryInvoice.generateTemporaryInvoiceId();

        App.getDataStore().addTemporaryInvoice(temporaryInvoice);

        System.out.println(customerId);

        temporaryInvoice = new TemporaryInvoice("");

        // SAVE TO DB
        updateDatastore();
        resetInfo();
        updateTemporaryInvoiceDropdown();
    }
    public void makeBill() {
        // Check customerId
        String customerId = customerDropdown.getDropdown().getValue();
        if (customerId == null || customerId.equals("New Customer")){
            Customer newCustomer = new Customer();
            newCustomer.generateCustomerId();
            customerId = newCustomer.getId();
            App.getDataStore().addClient(newCustomer);
        }

        // Create BoughtProduct list
        List<BoughtProduct> products = new ArrayList<>();

        // Parse TempBill product to products
        for (Map.Entry<String, Integer> entry : temporaryInvoice.getProductFrequencies().entrySet()) {
            String productId = entry.getKey();

            Optional<InventoryProduct> inventoryProduct = productList.stream()
                    .filter(ip -> ip.getId().equals(productId))
                    .findFirst();

            if (inventoryProduct.isPresent()) {
                InventoryProduct product = inventoryProduct.get();

                if (entry.getValue() > product.getStock()){
                    OkPopUp decreaseFrequency = new OkPopUp("The product stock is now " + product.getStock() + ". Your bill for " + product.getProductName() + " will be decreased from " + entry.getValue() + " to " + product.getStock());
                    entry.setValue(product.getStock());
                }

                if (entry.getValue() > 0){
                    BoughtProduct boughtProduct = new BoughtProduct(product, entry.getValue());
                    products.add(boughtProduct);

                    // DELETE FROM INVENTORY PRODUCT DATASTORE
                    App.getDataStore().decreaseProductStock(boughtProduct.getId(), boughtProduct.getCount());
                }
            }
        }

        // IF USER IS MEMBERS OR VIP, SHOW POP UP "WANT TO USE POINTS?"
        Customer customer = App.getDataStore().getCustomerById(customerId);
        Double usePoint = new Double((customer instanceof Member || customer instanceof VIP) ? ((Member)customer).getPoint() : 0.0);
        YesNoPopUp popUpUsePoint = new YesNoPopUp("Do you want you use your " + usePoint.toString() + " points?");
        if (customer != null && usePoint > 0){
            popUpUsePoint.getYesButton().setOnAction(e -> {
                popUpUsePoint.setValue(new Boolean(true));
                popUpUsePoint.closeStage();
            });
            popUpUsePoint.showStage();
        }

        if (popUpUsePoint.getValue() != null && popUpUsePoint.getValue()) {
            Double oldPoint = new Double(usePoint);
            usePoint = new Double(oldPoint);
            usePoint = new Double((oldPoint > grandTotal - discount) ? grandTotal - discount : oldPoint);
        } else {
            usePoint = new Double(0.0);
        }

        FixedInvoice invoice = new FixedInvoice(products, customerId, discount, usePoint);
        App.getDataStore().addInvoice(invoice);

        Double getPoint = new Double (0.01 * (grandTotal-usePoint));
        Double deltaPoint = new Double(getPoint - usePoint);
        if (customer instanceof VIP || customer instanceof Member) {
            App.getDataStore().updateClient((Member)customer, invoice.getId(), deltaPoint);
        } else {
            App.getDataStore().updateClient(customer, invoice.getId(), deltaPoint);
        }

        App.getDataStore().deleteTemporaryInvoices(this.temporaryInvoice);

        OkPopUp successMakeBill = new OkPopUp("Successfully Make Bill with id " + invoice.getId() + ", used " + usePoint + "points, get " + getPoint + "points");

        if (!temporaryInvoice.getCustomerId().equals("")){
            temporaryInvoice = new TemporaryInvoice("");
        } else {
            temporaryInvoice.getProductFrequencies().clear();
        }

        // SAVE TO DB
        updateDatastore();
        resetInfo();
        updateTemporaryInvoiceDropdown();
    }

    public void addProduct(Product product) {
        InventoryProduct storeProduct = App.getDataStore().getProductById(product.getId());
        int productCountToBe = 1;
        if (temporaryInvoice.getProductFrequencies().containsKey(product.getId())) {
            productCountToBe = temporaryInvoice.getProductFrequencies().get(product.getId()) + 1;
        }

        if (storeProduct != null && productCountToBe <= storeProduct.getStock()){
            temporaryInvoice.addProduct(product.getId());
        }

        updateNumbers();
        Integer productStockCount = temporaryInvoice.getProductFrequencies().get(product.getId());
        if (productStockCount != null && productStockCount > 0 ){
            transactionInvoiceCardList.addInvoiceCard(product);
        }
    }

    public void removeProduct(Product product) {
        temporaryInvoice.removeProduct(product.getId());
        updateNumbers();

        // Check if invoice need to be deleted
        if (!temporaryInvoice.getProductFrequencies().containsKey(product.getId())){
            transactionInvoiceCardList.removeInvoiceCard(product);
        }
    }

    public void updateNumbers() {
        updateDiscount();
        updateGrandTotal();
    }

    public void updateDiscount() {
        Customer customer = App.getDataStore().getCustomerById(customerDropdown.getDropdown().getValue());

        if (customer != null && customer.getType().equals("VIP")) {
            discount = new Double(temporaryInvoice.total() * 0.1);
        } else {
            discount = new Double(0.0);
        }

        discountContainer.getCardNumber().setText(String.format((discount == 0 ? "" : "- ") + "%.2f", discount));
    }

    public void updateGrandTotal() {
        grandTotal = temporaryInvoice.grandTotal() - discount;
        grandTotalNumber.setText(String.format("%.2f", grandTotal));
    }



    public void renderAdditionalCostsContainer() {
        for (Map.Entry<String, Double> cost : TemporaryInvoice.additionalCosts.entrySet()) {
            AdditionalCostCard additionalCostCard = new AdditionalCostCard(0.8 * managementContainerWidth, cost.getKey());
            Double number = new Double(cost.getValue() * 100);
            additionalCostCard.getCardNumber().setText(number.toString());
            additionalCostsContainer.getChildren().add(additionalCostCard);
        }
    }

    public void updateTemporaryInvoice(){
//        temporaryInvoice = invoice;

        resetInfo();

//        for ()
        for (Map.Entry<String, Integer> entry : temporaryInvoice.getProductFrequencies().entrySet()) {
            String productId = entry.getKey();

            InventoryProduct inventoryProduct = App.getDataStore().getInventory().getById(productId);

            if (inventoryProduct != null && inventoryProduct.getStatus()) {
                int frequency = entry.getValue();

                if (frequency > inventoryProduct.getStock()) {
                    OkPopUp decreaseFrequency = new OkPopUp("The product stock is now " + inventoryProduct.getStock() + ". Your bill for " + inventoryProduct.getProductName() + " will be decreased from " + frequency + " to " + inventoryProduct.getStock());
                    frequency = inventoryProduct.getStock();
                    entry.setValue(frequency);
                }

                for (int i = 0; i < frequency; i++) {
                    transactionInvoiceCardList.addInvoiceCard(inventoryProduct);
                }
            } else if (inventoryProduct != null) {
                // SHOW POP UP
                OkPopUp removeItem = new OkPopUp(inventoryProduct.getProductName() + "is currently not for sale, it will be remove from the bill");
            }
        }

        updateNumbers();
    }

    public void resetInfo() {
        // RESET THE GRAND TOTAL
        setGrandTotal(0.0);
        updateNumbers();

        transactionProductCardList.updateProductMap(productList);
        transactionProductCardList.renderTransactionProductCards();

        // RESET THE INVOICE CARDS CONTAINER
        transactionInvoiceCardList.getInvoiceListContainer().getChildren().clear();
    }
}