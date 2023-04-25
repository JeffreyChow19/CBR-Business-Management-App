package com.cbr.view;

import com.cbr.view.components.headermenu.HeaderMenuBar;
import com.cbr.view.components.tabmenu.TabMenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class MainView extends VBox {
    private final BorderPane bodyContainer;
    public MainView() {
        super();

        /* Header Setup */
        this.getChildren().add(new HeaderMenuBar());

        /* Body Setup */
        bodyContainer = new BorderPane();
        bodyContainer.setTop(new TabMenuBar());
        this.getChildren().add(bodyContainer);
    }
}
