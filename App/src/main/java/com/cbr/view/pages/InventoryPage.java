package com.cbr.view.pages;

import com.cbr.App;
import com.cbr.models.InventoryProduct;
import com.cbr.models.TemporaryInvoice;
import com.cbr.view.components.buttons.DefaultButton;
import com.cbr.view.components.buttons.PlusButton;
import com.cbr.view.components.cardslist.InventoryProductCardList;
import com.cbr.view.components.dropdown.Dropdown;
import com.cbr.view.components.labels.PageTitle;
import com.cbr.view.theme.Theme;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class InventoryPage extends StackPane {
    private InventoryProductCardList productCardList;

    public InventoryPage() {
        super();
        ScrollPane scroll_area = new ScrollPane();
        VBox container = new VBox();

        // Get datastore
        List<InventoryProduct> productList = App.getDataStore().getInventory().getDataList();
        productCardList = new InventoryProductCardList(productList);

        // Title
        PageTitle pageTitle = new PageTitle("Inventory Management");

        // Search and Filter
        HBox search_filter = new HBox();
        search_filter.setMaxWidth(0.75*Theme.getScreenWidth());

        // Searchbar
        TextField searchBar = new TextField();
        searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
            updateList(newValue.toLowerCase());
        });
        searchBar.setStyle("-fx-background-radius: 20;");
        searchBar.setPromptText("Search product by name");
        searchBar.setPadding(new Insets(12));
        searchBar.setMaxSize(Theme.getScreenWidth()*0.3, Theme.getScreenWidth()*0.025);
        searchBar.setMinSize(Theme.getScreenWidth()*0.3, Theme.getScreenWidth()*0.025);

        // Filter
        Set<String> categorySet = new HashSet<>();
        for (InventoryProduct product : productList) {
            categorySet.add(product.getCategory());
        }
        List<String> categoryList = new ArrayList<>(categorySet);

        Dropdown filterDropdown = new Dropdown(categoryList);
        filterDropdown.setPromptText("Select a category");

        filterDropdown.valueProperty().addListener((observable, oldValue, newValue) -> {
                    updateFilter(newValue.toLowerCase());
                });

        Button addItem = new DefaultButton(0.1*Theme.getScreenWidth(), 24.0, "Add New Item");
        Region region = new Region();
        search_filter.setHgrow(region, Priority.ALWAYS);

        search_filter.setSpacing(Theme.getScreenWidth()*0.025);
        search_filter.getChildren().addAll(searchBar, filterDropdown, region, addItem);
        search_filter.setAlignment(Pos.CENTER);

        // Add and set content
        container.getChildren().addAll(pageTitle, search_filter, productCardList);
        container.setAlignment(Pos.TOP_CENTER);
        container.prefWidthProperty().bind(scroll_area.widthProperty()); // bind the width of the VBox to the width of the ScrollPane
        container.prefHeightProperty().bind(scroll_area.heightProperty()); // bind the height of the VBox to the height of the ScrollPane
        container.setSpacing(50);
        container.setPadding(new Insets(50));
        container.setStyle("-fx-background-color:" + Theme.getPrimaryDark());

        scroll_area.setMinSize(Theme.getScreenWidth(), Theme.getScreenHeight());
        scroll_area.setContent(container);
        this.getChildren().add(scroll_area);
    }

    public void updateList(String search) {
        List<InventoryProduct> filteredProduct = App.getDataStore().getInventory().getDataList().stream()
                .filter(prod -> prod.getProductName().toLowerCase().contains(search)).collect(Collectors.toList());
        this.productCardList.updateList(filteredProduct);
    }

    public void updateFilter(String category) {
        List<InventoryProduct> filteredProduct = App.getDataStore().getInventory().getDataList().stream()
                .filter(prod -> prod.getCategory().toLowerCase().contains(category)).collect(Collectors.toList());
        this.productCardList.updateList(filteredProduct);
    }



}
