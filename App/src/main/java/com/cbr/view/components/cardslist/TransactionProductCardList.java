package com.cbr.view.components.cardslist;

import com.cbr.models.InventoryProduct;
import com.cbr.models.Product;
import com.cbr.view.components.cards.TransactionProductCard;
import com.cbr.view.pages.TransactionPage;
import com.cbr.view.theme.Theme;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TransactionProductCardList extends ScrollPane {
    private VBox container;
    private Map<String, List<InventoryProduct>> productMap;
    private double width;
    private double height;

    private TransactionPage parent;

    public TransactionProductCardList(TransactionPage parent, List<InventoryProduct> products){
        this.parent = parent;
        // Setup ScrollPane
        this.width = 0.5 * Theme.getScreenWidth();
        this.height = Theme.getScreenHeight();

        this.setMinSize(this.width, this.height);
        this.setPrefSize(this.width, this.height);
        this.setMaxSize(this.width, this.height);
        this.setBorder(null);
        this.setStyle("-fx-background-color:" + Theme.getPrimaryDark() + ";");
        this.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        this.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        this.setFitToWidth(true);

        container = new VBox();
        container.setMinWidth(this.width);
        container.setPrefWidth(this.width);
        container.setMaxWidth(this.width);
        container.setStyle("-fx-background-color:" + Theme.getPrimaryDark() + ";");
        container.setAlignment(Pos.CENTER);
        container.setPadding(new Insets(20, 0, 100, 0));

        // Update productMap
        this.updateProductMap(products);

        // Render Product Cards
        this.renderTransactionProductCards();

        this.setContent(container);
    }

    public void updateProductMap(List<InventoryProduct> products){
        List<InventoryProduct> filteredProducts = products.stream().filter(p -> p.getStatus()).collect(Collectors.toList());

        this.productMap = new HashMap<>();
        for (InventoryProduct product : filteredProducts) {
            String category = product.getCategory();
            List<InventoryProduct> productsInCategory = this.productMap.get(category);
            if (productsInCategory == null) {
                productsInCategory = new ArrayList<>();
                this.productMap.put(category, productsInCategory);
            }
            productsInCategory.add(product);
        }
    }

    public void renderTransactionProductCards() {
        VBox content = new VBox();
        content.setMinWidth(0.95 * this.width);
        content.setPrefWidth(0.95 * this.width);
        content.setMaxWidth(0.95 * this.width);
        content.setSpacing(20);

        for (Map.Entry<String, List<InventoryProduct>> entry : this.productMap.entrySet()) {
            String category = entry.getKey();
            List<InventoryProduct> productsInCategory = entry.getValue();

            Label categoryLabel = new Label(category);
            categoryLabel.setFont(Theme.getHeading2Font());
            categoryLabel.setStyle("-fx-text-fill: white;");
            categoryLabel.setPadding(new Insets(0, 0 , 0, 0.03 * this.width));

            FlowPane productsPane = new FlowPane();
            productsPane.setMinWidth(0.95 * this.width);
            productsPane.setPrefWidth(0.95 * this.width);
            productsPane.setMaxWidth(0.95 * this.width);
            productsPane.setHgap(0.03 * width);
            productsPane.setVgap(0.03 * width);
            productsPane.setPadding(new Insets(0, 0 , 40, 0.03 * this.width));

            for (InventoryProduct product : productsInCategory) {
                TransactionProductCard transactionProductCard = new TransactionProductCard(parent, product);
                productsPane.getChildren().add(transactionProductCard);
            }

            content.getChildren().addAll(categoryLabel, productsPane);
        }

        this.container.getChildren().add(content);
    }
}
