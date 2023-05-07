package com.cbr.view.components.cards;

import com.cbr.models.InventoryProduct;
import com.cbr.view.components.buttons.EditButton;
import com.cbr.view.components.header.tabmenu.TabMenuBar;
import com.cbr.view.components.labels.CircleLabel;
import com.cbr.view.components.labels.DefaultLabel;
import com.cbr.view.components.labels.ToolTipLabel;
import com.cbr.view.pages.InventoryPage;
import com.cbr.view.pages.ItemEditorPage;
import com.cbr.view.theme.Theme;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class InventoryProductCard extends VBox {

    public InventoryProductCard(InventoryProduct product){
        double width = 0.18 * Theme.getScreenWidth();
        double height = 0.5 * Theme.getScreenHeight();

        double vpadding = 0.036 * height;
        double hpadding = 0.075 * width;

        // Set Card Size
        this.setMinSize(width, height);
        this.setPrefSize(width, height);
        this.setMaxSize(width, height);
        this.setPadding(new Insets(vpadding, hpadding, vpadding, hpadding));
        this.setSpacing(12);

        // Edit page
        ItemEditorPage editorPage = new ItemEditorPage(product);

        // Edit Button
        HBox edit_container = new HBox();
        Region edit_region = new Region();
        edit_container.setHgrow(edit_region, Priority.ALWAYS);
        Button editButton = new EditButton(hpadding);
        editButton.setOnAction(event -> TabMenuBar.getInstance().addTab("Edit Item #" + product.getId(), editorPage));
        if (!product.getStatus()) {
            editButton.setDisable(true);
        } else {
            editButton.setDisable(false);
        }
        edit_container.getChildren().addAll(edit_region, editButton);
        this.getChildren().add(edit_container);

        // Product Image
        Image image = new Image(product.getImagePath());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(0.45 * height);
        imageView.setFitHeight(0.45 * height);
        Rectangle clip = new Rectangle(imageView.getFitWidth(), imageView.getFitHeight());
        imageView.setClip(clip);
        this.getChildren().add(imageView);

        // Product Name and Stock HBox
        HBox name_stock = new HBox();
        // Product Name
        Label productLabel = new ToolTipLabel(product.getProductName());
        productLabel.setFont(Theme.getBodyBoldFont());
        productLabel.setTextFill(Color.WHITE);
        // Stock
        Region region = new Region();
        name_stock.setHgrow(region, Priority.ALWAYS);
        Label stockLabel = new CircleLabel(hpadding, product.getStock().toString(), Theme.getAccentBlue());
        name_stock.setAlignment(Pos.CENTER);
        name_stock.getChildren().addAll(productLabel, region, stockLabel);
        this.getChildren().add(name_stock);

        // Price Details HBox
        HBox price_details = new HBox();
        // Price Labels VBox
        VBox price_labels = new VBox();
        Label hargaBeliLabel = new Label("Buy price");
        hargaBeliLabel.setFont(Theme.getBodyFont());
        hargaBeliLabel.setTextFill(Color.WHITE);
        Label hargaJualLabel = new Label("Sell price");
        hargaJualLabel.setFont(Theme.getBodyFont());
        hargaJualLabel.setTextFill(Color.WHITE);
        price_labels.getChildren().addAll(hargaBeliLabel, hargaJualLabel);
        // Price Number VBox
        VBox price_number = new VBox();
        Label hargaBeliNumber = new Label();
        hargaBeliNumber.setText(product.getBuyPrice().toString());
        hargaBeliNumber.setFont(Theme.getBodyFont());
        hargaBeliNumber.setTextFill(Color.WHITE);
        Label hargaJualNumber = new Label();
        hargaJualNumber.setText(product.getSellPrice().toString());
        hargaJualNumber.setFont(Theme.getBodyFont());
        hargaJualNumber.setTextFill(Color.WHITE);
        price_number.getChildren().addAll(hargaBeliNumber, hargaJualNumber);

        Region price_region = new Region();
        price_details.setHgrow(price_region, Priority.ALWAYS);
        price_details.getChildren().addAll(price_labels, price_region, price_number);
        this.getChildren().add(price_details);

        // Labels HBox
        HBox product_labels = new HBox();
        // Category Label
        Label categoryLabel = new DefaultLabel(0.4 * width,product.getCategory(), Theme.getAccentBlue());
        // Status Label
        Label statusLabel;
        if (product.getStatus()) {
            statusLabel = new DefaultLabel(0.4 * width,"Active", Theme.getAccentGreen());
        }
        else {
            statusLabel = new DefaultLabel(0.4 * width,"Inactive", Theme.getAccentRed());
        }
        product_labels.setSpacing(8);
        product_labels.getChildren().addAll(categoryLabel, statusLabel);
        this.getChildren().add(product_labels);

        this.setAlignment(Pos.CENTER);

        this.setStyle("-fx-background-color:" + Theme.getSecondaryBase() + ";  -fx-background-radius: 10;");
    }
}
