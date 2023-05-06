package com.cbr.view.components.cardslist;

import com.cbr.models.InventoryProduct;
import com.cbr.view.components.cards.InventoryProductCard;
import com.cbr.view.theme.Theme;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;

import java.util.List;

public class InventoryProductCardList extends FlowPane {

    private List<InventoryProduct> productsList;
    public InventoryProductCardList(List<InventoryProduct> products) {
        // Set Size
        double width = 0.18 *4* Theme.getScreenWidth() + (3 * 0.02 * 0.8 * Theme.getScreenWidth());
        this.setMinWidth(width);
        this.setMaxWidth(width);
        this.setHgap(0.018 * 0.8 * Theme.getScreenWidth());
        this.setVgap(0.018 * 0.8 * Theme.getScreenWidth());
        // Set attribute
        this.productsList = products;
        this.updateChildren();
    }

    public void updateList(List<InventoryProduct> products) {
        this.productsList = products;
        this.updateChildren();
    }

    public void updateChildren() {
        this.getChildren().clear();
        for (InventoryProduct product: productsList) {
            InventoryProductCard inventoryProductCard = new InventoryProductCard(product);
            this.getChildren().add(inventoryProductCard);
        }
        if (this.productsList.isEmpty()) {
            Label notFound = new Label("No match");
            notFound.setFont(Theme.getBodyMediumFont());
            notFound.setTextFill(Color.WHITE);
            this.getChildren().add(notFound);
        }
    }

}
