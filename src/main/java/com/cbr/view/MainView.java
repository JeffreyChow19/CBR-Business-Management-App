package com.cbr.view;

import com.cbr.view.components.header.HeaderMenuBar;
import com.cbr.view.components.header.HeaderTabBar;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class MainView extends VBox {
    private VBox headerContainer;
    private StackPane bodyContainer;
    public MainView() {
        super();

        /* Header Setup */
        headerContainer = new VBox();
        headerContainer.getChildren().addAll(new HeaderMenuBar(), new HeaderTabBar());

        /* Body Setup */
        bodyContainer = new StackPane();
        bodyContainer.getChildren().add(new Label("Example Body"));
        this.getChildren().addAll(headerContainer, bodyContainer);
    }
}
