package com.cbr.view.pages;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class ClientsPage extends StackPane {
    public ClientsPage() {
        super();
        this.getChildren().add(new Label("Clients List"));
    }
}
