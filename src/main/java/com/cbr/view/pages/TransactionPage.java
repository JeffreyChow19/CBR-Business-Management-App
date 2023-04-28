package com.cbr.view.pages;

import com.cbr.view.components.cards.TransactionProductCard;
import com.cbr.view.components.dropdown.Dropdown;
import com.cbr.view.theme.Theme;
import com.cbr.App;

import javafx.scene.layout.VBox;

import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import com.cbr.models.*;
import com.cbr.view.components.cardslist.CustomerCardList;
import com.cbr.view.components.cardslist.MemberCardList;
import com.cbr.view.theme.Theme;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
public class TransactionPage extends StackPane {
    private HBox container;
    public TransactionPage() {
        super();

        container = new HBox();
        container.setAlignment(Pos.CENTER);
        container.setSpacing(10);
        container.setPadding(new Insets(10));

        Product product = new Product();
        product.setProductName("Indomie Goreng Rendang");
        TransactionProductCard transactionProductCard = new TransactionProductCard(product);
        container.getChildren().add(transactionProductCard);

        Dropdown dropdown = new Dropdown();
        container.getChildren().add(dropdown);

        container.setAlignment(Pos.TOP_CENTER);
        container.prefWidthProperty().bind(this.widthProperty()); // bind the width of the HBox to the width of the ScrollPane
        container.prefHeightProperty().bind(this.heightProperty()); // bind the height of the HBox to the height of the ScrollPane
        container.setSpacing(50);
        container.setPadding(new Insets(50));
        container.setStyle("-fx-background-color:" + Theme.getPrimaryDark());

        this.setMinSize(Theme.getScreenWidth(), Theme.getScreenHeight());
        this.getChildren().add(container);
    }
}
