package com.cbr.view.components.cards.clientcard;

import com.cbr.view.theme.Theme;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

import java.util.List;

public class ClientCard extends HBox {
    public ClientCard(List<Node> nodes, List<Button> buttons){
        for (Node n : nodes){
            if (n!=null){
                this.getChildren().add(n);
            }
        }
        Region region = new Region();
        this.setHgrow(region, Priority.ALWAYS);
        this.getChildren().add(region);
        for (Button b : buttons){
            if (b!=null){
                this.getChildren().add(b);
            }
        }
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
