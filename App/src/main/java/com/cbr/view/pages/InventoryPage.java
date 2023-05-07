package com.cbr.view.pages;

import com.cbr.App;
import com.cbr.models.*;
import com.cbr.view.components.buttons.DefaultButton;
import com.cbr.view.components.cardslist.InventoryProductCardList;
import com.cbr.view.components.form.dropdown.Dropdown;
import com.cbr.view.components.header.tabmenu.TabMenuBar;
import com.cbr.view.components.labels.PageTitle;
import com.cbr.view.theme.Theme;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class InventoryPage extends StackPane {
    private InventoryProductCardList productCardList;
    private String searchQuery;
    private String categoryFilter;

    public InventoryPage() {
        super();
        searchQuery = "";
        categoryFilter = "";

        ScrollPane scroll_area = new ScrollPane();
        VBox container = new VBox();

        // Get datastore
        List<InventoryProduct> productList = App.getDataStore().getInventory().getDataList();
        productCardList = new InventoryProductCardList(productList);

        // Title
        PageTitle pageTitle = new PageTitle("Inventory Management");

        // Search and Filter
        HBox search_filter = new HBox();
        search_filter.setMaxWidth(0.75 * Theme.getScreenWidth());

        // Searchbar
        TextField searchBar = new TextField();
        searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
            searchQuery = newValue;
        });
        searchBar.setStyle("-fx-background-radius: 20;");
        searchBar.setPromptText("Search product by name");
        searchBar.setPadding(new Insets(12));
        searchBar.setMaxSize(Theme.getScreenWidth() * 0.3, Theme.getScreenWidth() * 0.025);
        searchBar.setMinSize(Theme.getScreenWidth() * 0.3, Theme.getScreenWidth() * 0.025);

        // Filter
        Set<String> categorySet = new HashSet<>();
        for (InventoryProduct product : productList) {
            categorySet.add(product.getCategory());
        }
        List<String> categoryList = new ArrayList<>(categorySet);

        Dropdown filterDropdown = new Dropdown(categoryList);
        filterDropdown.setPromptText("Select a category");

        filterDropdown.valueProperty().addListener((observable, oldValue, newValue) -> {
            categoryFilter = newValue;
        });

        AddItemPage addItemPage = new AddItemPage();

        Button addItem = new DefaultButton(0.1 * Theme.getScreenWidth(), 24.0, "Add New Item");
        addItem.setOnAction(event -> TabMenuBar.getInstance().addTab("Add Item", addItemPage));
        Region region = new Region();
        HBox.setHgrow(region, Priority.ALWAYS);

        search_filter.setSpacing(Theme.getScreenWidth() * 0.025);
        search_filter.getChildren().addAll(searchBar, filterDropdown, region, addItem);
        search_filter.setAlignment(Pos.CENTER);

        // Add and set content
        container.getChildren().addAll(pageTitle, search_filter, productCardList);
        container.setAlignment(Pos.TOP_CENTER);
        container.prefWidthProperty().bind(scroll_area.widthProperty()); // bind the width of the VBox to the width of
                                                                         // the ScrollPane
        container.prefHeightProperty().bind(scroll_area.heightProperty()); // bind the height of the VBox to the height
                                                                           // of the ScrollPane
        container.setSpacing(50);
        container.setPadding(new Insets(50));
        container.setStyle("-fx-background-color:" + Theme.getPrimaryDark());

        scroll_area.setMinSize(Theme.getScreenWidth(), Theme.getScreenHeight());
        scroll_area.setContent(container);
        this.getChildren().add(scroll_area);

        Thread task = new Thread(() -> {
            while (true) {
                List<InventoryProduct> filteredProduct = App.getDataStore().getInventory().getDataList().stream()
                        .filter(prod -> prod.getProductName().toLowerCase().contains(searchQuery)
                                && prod.getCategory().toLowerCase().contains(categoryFilter))
                        .collect(Collectors.toList());

                Platform.runLater(() -> {
                    this.productCardList.updateList(filteredProduct);
                });
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        task.start();
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
