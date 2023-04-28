package com.cbr.view.components.cards;

import com.cbr.models.Customer;
import com.cbr.view.components.buttons.HistoryButton;
import com.cbr.view.components.buttons.UpgradeButton;
import com.cbr.view.theme.Theme;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

public class CustomerCard extends HBox {
    public CustomerCard(Customer customer){
        Label customerId = new Label("Customer#"+customer.getId());
        customerId.setFont(Theme.getHeading2Font());
        customerId.setTextFill(Color.WHITE);

        Region region = new Region();
        this.setHgrow(region, Priority.ALWAYS);
        Button history = new HistoryButton(customer.getId());
        Button upgrade = new UpgradeButton(customer.getId());

        this.getChildren().addAll(customerId, region, history, upgrade);

        Insets margin = new Insets(10); // set the margin
        for (Node child : this.getChildren()) {
            HBox.setMargin(child, margin); // set the margin for each child node
        }

        this.setPadding(new Insets(20));
        this.setAlignment(Pos.CENTER);
        this.setMaxWidth(Theme.getScreenWidth()*0.7);
        this.setHeight(Theme.getScreenHeight()*0.35);
        this.setStyle("-fx-background-color:" + Theme.getPrimaryBase() + ";  -fx-background-radius: 10;");
    }
}
